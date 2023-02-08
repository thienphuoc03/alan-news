package com.htphuoc.alannewsserver.Service;

import com.htphuoc.alannewsserver.Model.User;
import com.htphuoc.alannewsserver.Payload.Request.RoleRequest;
import com.htphuoc.alannewsserver.Payload.Response.ApiResponse;
import com.htphuoc.alannewsserver.Payload.Response.PagedResponse;
import com.htphuoc.alannewsserver.Payload.Response.UserProfileResponse;
import com.htphuoc.alannewsserver.Payload.Response.UserResponse;
import com.htphuoc.alannewsserver.Security.UserDetailsImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public interface UserService {
    UserProfileResponse getUserProfile(String username);

    User addUser(User user);

    User updateUser(User newUser, Long id, UserDetailsImpl currentUser);

    ApiResponse deleteUser(Long id, UserDetailsImpl currentUser);

    ResponseEntity<UserResponse> getUserById(Long id);

    PagedResponse<UserResponse> getAllUser(Integer page, Integer size, String sortBy);

    ApiResponse giveRole(RoleRequest roleRequest, UserDetailsImpl currentUser);
}
