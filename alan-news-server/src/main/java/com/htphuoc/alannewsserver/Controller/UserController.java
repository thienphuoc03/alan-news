package com.htphuoc.alannewsserver.Controller;

import com.htphuoc.alannewsserver.Model.Album;
import com.htphuoc.alannewsserver.Model.Post;
import com.htphuoc.alannewsserver.Model.User;
import com.htphuoc.alannewsserver.Payload.Request.RoleRequest;
import com.htphuoc.alannewsserver.Payload.Response.ApiResponse;
import com.htphuoc.alannewsserver.Payload.Response.PagedResponse;
import com.htphuoc.alannewsserver.Payload.Response.UserProfileResponse;
import com.htphuoc.alannewsserver.Payload.Response.UserResponse;
import com.htphuoc.alannewsserver.Security.CurrentUser;
import com.htphuoc.alannewsserver.Security.UserDetailsImpl;
import com.htphuoc.alannewsserver.Service.AlbumService;
import com.htphuoc.alannewsserver.Service.PostService;
import com.htphuoc.alannewsserver.Service.UserService;
import com.htphuoc.alannewsserver.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Autowired
    private AlbumService albumService;

    @GetMapping("/{username}/profile")
    public ResponseEntity<UserProfileResponse> getUserProfile(
            @PathVariable(value = "username") String username) {
        UserProfileResponse userProfile = userService.getUserProfile(username);

        return new ResponseEntity<>(userProfile, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(
            @PathVariable(name = "id") Long id) {
        return userService.getUserById(id);
    }

    @GetMapping
    public PagedResponse<UserResponse> getAllUser(
            @RequestParam(name = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) Integer page,
            @RequestParam(name = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) Integer size,
            @RequestParam(name = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY) String sortBy) {
        return userService.getAllUser(page, size, sortBy);
    }

    @GetMapping("/{username}/posts")
    public ResponseEntity<PagedResponse<Post>> getPostsCreatedBy(
            @PathVariable(value = "username") String username,
            @RequestParam(value = "page", required = false, defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) Integer page,
            @RequestParam(value = "size", required = false, defaultValue = AppConstants.DEFAULT_PAGE_SIZE) Integer size,
            @RequestParam(name = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY) String sortBy) {
        PagedResponse<Post> response = postService.getPostsByCreatedBy(username, page, size, sortBy);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{username}/albums")
    public ResponseEntity<PagedResponse<Album>> getUserAlbums(
            @PathVariable(name = "username") String username,
            @RequestParam(name = "page", required = false, defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) Integer page,
            @RequestParam(name = "size", required = false, defaultValue = AppConstants.DEFAULT_PAGE_SIZE) Integer size,
            @RequestParam(name = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY) String sortBy) {
        PagedResponse<Album> response = albumService.getUserAlbums(username, page, size, sortBy);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasRole('MODERATOR')")
    public ResponseEntity<User> addUser(@Valid @RequestBody User user) {
        User newUser = userService.addUser(user);

        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR')")
    public ResponseEntity<User> updateUser(
            @Valid @RequestBody User newUser,
            @PathVariable(name = "id") Long id,
            @CurrentUser UserDetailsImpl currentUser) {
        User updatedUSer = userService.updateUser(newUser, id, currentUser);

        return new ResponseEntity<>(updatedUSer, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> deleteUser(
            @PathVariable(name = "id") Long id,
            @CurrentUser UserDetailsImpl currentUser) {
        ApiResponse apiResponse = userService.deleteUser(id, currentUser);

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping("/give-role")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> giveRole(
            @Valid @RequestBody RoleRequest roleRequest,
            @CurrentUser UserDetailsImpl currentUser) {
        ApiResponse apiResponse = userService.giveRole(roleRequest, currentUser);

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
