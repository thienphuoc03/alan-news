package com.htphuoc.alannewsserver.Service.Impl;

import com.htphuoc.alannewsserver.Exception.TokenRefreshException;
import com.htphuoc.alannewsserver.Model.ERole;
import com.htphuoc.alannewsserver.Model.RefreshToken;
import com.htphuoc.alannewsserver.Model.Role;
import com.htphuoc.alannewsserver.Model.User;
import com.htphuoc.alannewsserver.Payload.Request.LoginRequest;
import com.htphuoc.alannewsserver.Payload.Request.SignupRequest;
import com.htphuoc.alannewsserver.Payload.Request.TokenRefreshRequest;
import com.htphuoc.alannewsserver.Payload.Response.JwtResponse;
import com.htphuoc.alannewsserver.Payload.Response.TokenRefreshResponse;
import com.htphuoc.alannewsserver.Repository.RoleRepository;
import com.htphuoc.alannewsserver.Repository.UserRepository;
import com.htphuoc.alannewsserver.Security.JwtTokenProvider;
import com.htphuoc.alannewsserver.Security.RefreshTokenService;
import com.htphuoc.alannewsserver.Security.UserDetailsImpl;
import com.htphuoc.alannewsserver.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    RefreshTokenService refreshTokenService;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Override
    public ResponseEntity<?> authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetailsImpl = (UserDetailsImpl) authentication.getPrincipal();

        String jwt = jwtTokenProvider.generateToken(authentication);

        List<String> roles = userDetailsImpl.getAuthorities().stream().map(item -> item.getAuthority())
                .collect(Collectors.toList());

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetailsImpl.getId());

        return ResponseEntity.ok(new JwtResponse(jwt, refreshToken.getToken(), userDetailsImpl.getId(),
                userDetailsImpl.getUsername(), userDetailsImpl.getEmail(), roles));
    }

    @Override
    public ResponseEntity<?> registerUser(SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest().body("Error: Username is already taken!");
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body("Error: Email is already in use!");
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok("User registered successfully!");
    }

    @Override
    public ResponseEntity<?> refreshToken(TokenRefreshRequest tokenRefreshRequest) {
        String requestRefreshToken = tokenRefreshRequest.getRefreshToken();

        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String token = jwtTokenProvider.generateTokenFromUsername(user.getUsername());
                    return ResponseEntity.ok(new TokenRefreshResponse(token, requestRefreshToken));
                })
                .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
                        "Refresh token is not in database!"));
    }

    @Override
    public ResponseEntity<?> logoutUser() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            if (username != null) {
                refreshTokenService.deleteByUsername(username);
                return ResponseEntity.ok("Log out successful!");
            }
        } catch (Exception exception) {
            System.out.println(exception);
        }


//        UserDetailsImpl userDetailsImpl =
//                (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//        Long userId = userDetailsImpl.getId();
//        refreshTokenService.deleteByUserId(userId);
        return null;
    }
}
