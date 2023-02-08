package com.htphuoc.alannewsserver.Service.Impl;

import com.htphuoc.alannewsserver.Model.Post;
import com.htphuoc.alannewsserver.Payload.Response.PagedResponse;
import com.htphuoc.alannewsserver.Repository.CategoryRepository;
import com.htphuoc.alannewsserver.Repository.PostRepository;
import com.htphuoc.alannewsserver.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public PagedResponse<Post> getPostsByCreatedBy(String username, Integer page, Integer size, String sortBy) {
        return null;
    }
}
