package com.htphuoc.alannewsserver.Service.Impl;

import com.htphuoc.alannewsserver.Exception.NotFoundException;
import com.htphuoc.alannewsserver.Exception.SignatureException;
import com.htphuoc.alannewsserver.Model.Category;
import com.htphuoc.alannewsserver.Model.ERole;
import com.htphuoc.alannewsserver.Model.Post;
import com.htphuoc.alannewsserver.Model.User;
import com.htphuoc.alannewsserver.Payload.Request.PostRequest;
import com.htphuoc.alannewsserver.Payload.Response.ApiResponse;
import com.htphuoc.alannewsserver.Payload.Response.PagedResponse;
import com.htphuoc.alannewsserver.Payload.Response.PostResponse;
import com.htphuoc.alannewsserver.Repository.CategoryRepository;
import com.htphuoc.alannewsserver.Repository.PostRepository;
import com.htphuoc.alannewsserver.Repository.UserRepository;
import com.htphuoc.alannewsserver.Security.UserDetailsImpl;
import com.htphuoc.alannewsserver.Service.PostService;
import com.htphuoc.alannewsserver.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public PagedResponse<Post> getPostsByCreatedBy(String username, Integer page, Integer size, String sortBy) {
        try {
            User user = userRepository.getUserByUserName(username);
            Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).descending());
            Page<Post> posts = postRepository.findByCreatedBy(user.getId(), pageable);

            List<Post> content = posts.getNumberOfElements() == 0 ? Collections.emptyList() : posts.getContent();

            return new PagedResponse<>(content, posts.getNumber(), posts.getSize(), posts.getTotalElements(),
                    posts.getTotalPages(), posts.isLast());
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public PagedResponse<Post> getAllPosts(Integer page, Integer size, String sortBy) {
        try {
            Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).descending());

            Page<Post> posts = postRepository.findAll(pageable);

            List<Post> content = posts.getNumberOfElements() == 0 ? Collections.emptyList() : posts.getContent();

            return new PagedResponse<>(content, posts.getNumber(), posts.getSize(), posts.getTotalElements(),
                    posts.getTotalPages(), posts.isLast());
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public PagedResponse<Post> getPostsByCategory(Long id, Integer page, Integer size, String sortBy) {
        try {
            Category category = categoryRepository.findById(id).orElseThrow(() ->
                    new NotFoundException("Not found category with id: " + id));

            Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).descending());
            Page<Post> posts = postRepository.findByCategory(category.getId(), pageable);

            List<Post> content = posts.getNumberOfElements() == 0 ? Collections.emptyList() : posts.getContent();

            return new PagedResponse<>(content, posts.getNumber(), posts.getSize(), posts.getTotalElements(),
                    posts.getTotalPages(), posts.isLast());
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public Post getPostById(Long id) {
        try {
            Post post = postRepository.findById(id).orElseThrow(() ->
                    new NotFoundException("Not found post with id: " + id));
            return post;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public PostResponse addPost(PostRequest postRequest, UserDetailsImpl currentUser) {
        try {
            User user = userRepository.findById(currentUser.getId())
                    .orElseThrow(() -> new NotFoundException("Not found category with id: " + postRequest.getCategoryId()));
            Category category = categoryRepository.findById(postRequest.getCategoryId())
                    .orElseThrow(() -> new NotFoundException("Not found category with id: " + postRequest.getCategoryId()));

            Post post = new Post();
            post.setBody(postRequest.getBody());
            post.setTitle(postRequest.getTitle());
            post.setThumbnail(postRequest.getThumbnail());
            post.setDescription(postRequest.getDescription());
            post.setCategory(category);
            post.setUser(user);

            Post newPost = postRepository.save(post);

            PostResponse postResponse = new PostResponse();

            postResponse.setTitle(newPost.getTitle());
            postResponse.setBody(newPost.getBody());
            postResponse.setCategory(newPost.getCategory().getName());

            return postResponse;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public Post updatePost(Long id, PostRequest newPostRequest, UserDetailsImpl currentUser) {
        try {
            Post post = postRepository.findById(id).orElseThrow(() ->
                    new NotFoundException("Not found post with id: " + id));
            Category category = categoryRepository.findById(newPostRequest.getCategoryId()).orElseThrow(() ->
                    new NotFoundException("Not found category with id: " + newPostRequest.getCategoryId()));
            if (post.getUser().getId().equals(currentUser.getId())
                    || currentUser.getAuthorities().contains(new SimpleGrantedAuthority(ERole.ROLE_MODERATOR.toString()))) {
                post.setTitle(newPostRequest.getTitle());
                post.setBody(newPostRequest.getBody());
                post.setCategory(category);
                return postRepository.save(post);
            }

            throw new SignatureException(AppConstants.YOU_DONT_HAVE_PERMISSION_TO + " edit this post");
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public ApiResponse deletePost(Long id, UserDetailsImpl currentUser) {
        try {
            Post post = postRepository.findById(id).orElseThrow(() ->
                    new NotFoundException("Not found post with id: " + id));
            if (post.getUser().getId().equals(currentUser.getId())
                    || currentUser.getAuthorities().contains(new SimpleGrantedAuthority(ERole.ROLE_MODERATOR.toString()))) {
                postRepository.deleteById(id);
                return new ApiResponse(Boolean.TRUE, "You successfully deleted post");
            }

            throw new SignatureException(AppConstants.YOU_DONT_HAVE_PERMISSION_TO + " delete this post");
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}
