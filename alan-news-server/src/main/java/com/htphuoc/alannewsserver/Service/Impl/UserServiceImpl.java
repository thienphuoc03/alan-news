package com.htphuoc.alannewsserver.Service.Impl;

import com.htphuoc.alannewsserver.Exception.BadRequestException;
import com.htphuoc.alannewsserver.Exception.NotFoundException;
import com.htphuoc.alannewsserver.Exception.SignatureException;
import com.htphuoc.alannewsserver.Model.ERole;
import com.htphuoc.alannewsserver.Model.Role;
import com.htphuoc.alannewsserver.Model.User;
import com.htphuoc.alannewsserver.Payload.Request.RoleRequest;
import com.htphuoc.alannewsserver.Payload.Response.ApiResponse;
import com.htphuoc.alannewsserver.Payload.Response.PagedResponse;
import com.htphuoc.alannewsserver.Payload.Response.UserProfileResponse;
import com.htphuoc.alannewsserver.Payload.Response.UserResponse;
import com.htphuoc.alannewsserver.Repository.PostRepository;
import com.htphuoc.alannewsserver.Repository.RoleRepository;
import com.htphuoc.alannewsserver.Repository.UserRepository;
import com.htphuoc.alannewsserver.Security.UserDetailsImpl;
import com.htphuoc.alannewsserver.Service.UserService;
import com.htphuoc.alannewsserver.utils.AppConstants;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserProfileResponse getUserProfile(String username) {
        try {
            User user = userRepository.getUserByUserName(username);

            Long postCount = postRepository.countByCreatedBy(user.getId());

            return new UserProfileResponse(user.getId(), user.getUsername(), user.getFirstName(), user.getLastName(),
                    user.getDob(), user.getGender(), user.getPhoneNumber(), user.getEmail(), user.getAddress(),
                    user.getAvatar(), user.getCreatedAt(), postCount);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public User addUser(User user) {
        try {
            if (userRepository.existsByUsername(user.getUsername())) {
                throw new BadRequestException("Username is already taken");
            }

            if (userRepository.existsByEmail(user.getEmail())) {
                throw new BadRequestException("Email is already taken");
            }

            Set<Role> roles = new HashSet<>();
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);

            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public User updateUser(User newUser, Long id, UserDetailsImpl currentUser) {
        try {
            User user = userRepository.findById(id).orElseThrow(() ->
                    new NotFoundException("Not found user with id: " + id));
            if (user.getId().equals(currentUser.getId())
                    || currentUser.getAuthorities().contains(new SimpleGrantedAuthority(ERole.ROLE_MODERATOR.toString()))) {

                modelMapper.map(newUser, user);
                user.setPassword(passwordEncoder.encode(newUser.getPassword()));

                return userRepository.save(user);

            }

            throw new SignatureException(AppConstants.YOU_DONT_HAVE_PERMISSION_TO + "  update this user");
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public ApiResponse deleteUser(Long id, UserDetailsImpl currentUser) {
        try {
            User user = userRepository.findById(id).orElseThrow(() ->
                    new NotFoundException("Not found user with id: " + id));
            if (user.getId().equals(currentUser.getId())
                    || currentUser.getAuthorities().contains(new SimpleGrantedAuthority(ERole.ROLE_ADMIN.toString()))
                    || currentUser.getAuthorities().contains(new SimpleGrantedAuthority(ERole.ROLE_MODERATOR.toString()))
            ) {
                userRepository.deleteById(user.getId());

                return new ApiResponse(Boolean.TRUE, "User deleted successfully");
            }

            throw new SignatureException(AppConstants.YOU_DONT_HAVE_PERMISSION_TO + " delete this user");
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public ApiResponse giveRole(RoleRequest roleRequest, UserDetailsImpl currentUser) {
        try {
            User user = userRepository.findById(roleRequest.getUserId()).orElseThrow(() ->
                    new NotFoundException("Not found user with id: " + roleRequest.getUserId()));

            if (currentUser.getAuthorities().contains(new SimpleGrantedAuthority(ERole.ROLE_ADMIN.toString()))) {
                Long roleId = roleRequest.getRoleId();
                Set<Role> roles = new HashSet<>();

                Role userRole = roleRepository.findById(roleRequest.getRoleId()).orElseThrow(() ->
                        new RuntimeException("Error: Role is not found."));

                roles.add(userRole);
                userRepository.save(user);
                return new ApiResponse(Boolean.TRUE, "You gave" + userRole.getName() + " role to user: "
                        + user.getUsername());
            }

            throw new SignatureException(AppConstants.YOU_DONT_HAVE_PERMISSION_TO + " use this function");
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public ResponseEntity<UserResponse> getUserById(Long id) {
        try {
            User user = userRepository.findById(id).orElseThrow(() ->
                    new NotFoundException("Not found user with id: " + id));

            return new ResponseEntity<UserResponse>((MultiValueMap<String, String>) user, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public PagedResponse<UserResponse> getAllUser(Integer page, Integer size, String sortBy) {
        try {
            Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, sortBy);
            Page<User> users = userRepository.findAll(pageable);

            if (users.getNumberOfElements() == 0) {
                return new PagedResponse<>(Collections.emptyList(), users.getNumber(), users.getSize(), users.getTotalElements(),
                        users.getTotalPages(), users.isLast());
            }
            List<UserResponse> userResponses = Arrays.asList(modelMapper.map(users.getContent(), UserResponse[].class));

            return new PagedResponse<>(userResponses, users.getNumber(), users.getSize(), users.getTotalElements(),
                    users.getTotalPages(), users.isLast());
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}
