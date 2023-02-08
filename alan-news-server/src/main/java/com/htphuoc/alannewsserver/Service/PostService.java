package com.htphuoc.alannewsserver.Service;

import com.htphuoc.alannewsserver.Model.Post;
import com.htphuoc.alannewsserver.Payload.Request.PostRequest;
import com.htphuoc.alannewsserver.Payload.Response.ApiResponse;
import com.htphuoc.alannewsserver.Payload.Response.PagedResponse;
import com.htphuoc.alannewsserver.Payload.Response.PostResponse;
import com.htphuoc.alannewsserver.Security.UserDetailsImpl;
import org.springframework.stereotype.Component;

@Component
public interface PostService {
    PagedResponse<Post> getPostsByCreatedBy(String username, Integer page, Integer size, String sortBy);

    PagedResponse<Post> getAllPosts(Integer page, Integer size, String sortBy);

    PagedResponse<Post> getPostsByCategory(Long id, Integer page, Integer size, String sortBy);

    Post getPostById(Long id);

    Post updatePost(Long id, PostRequest newPostRequest, UserDetailsImpl currentUser);

    ApiResponse deletePost(Long id, UserDetailsImpl currentUser);

    PostResponse addPost(PostRequest postRequest, UserDetailsImpl currentUser);
}
