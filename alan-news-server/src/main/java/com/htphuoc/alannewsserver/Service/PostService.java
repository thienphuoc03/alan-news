package com.htphuoc.alannewsserver.Service;

import com.htphuoc.alannewsserver.Model.Post;
import com.htphuoc.alannewsserver.Payload.Response.PagedResponse;
import org.springframework.stereotype.Component;

@Component
public interface PostService {
    PagedResponse<Post> getPostsByCreatedBy(String username, Integer page, Integer size, String sortBy);
}
