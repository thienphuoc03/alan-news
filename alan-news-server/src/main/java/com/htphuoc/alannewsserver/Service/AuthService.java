package com.htphuoc.alannewsserver.Service;

import com.htphuoc.alannewsserver.Payload.Request.LoginRequest;
import com.htphuoc.alannewsserver.Payload.Request.SignupRequest;
import com.htphuoc.alannewsserver.Payload.Request.TokenRefreshRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public interface AuthService {
    ResponseEntity<?> authenticateUser(LoginRequest loginRequest);

    ResponseEntity<?> registerUser(SignupRequest signUpRequest);

    ResponseEntity<?> refreshToken(TokenRefreshRequest tokenRefreshRequest);

    ResponseEntity<?> logoutUser();

}
