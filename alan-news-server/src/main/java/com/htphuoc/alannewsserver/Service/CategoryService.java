package com.htphuoc.alannewsserver.Service;

import com.htphuoc.alannewsserver.Model.Category;
import com.htphuoc.alannewsserver.Payload.Response.PagedResponse;
import com.htphuoc.alannewsserver.Security.UserDetailsImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public interface CategoryService {
    PagedResponse<Category> getAllCategory(int page, int size, String sortBy);

    ResponseEntity<Category> getCategoryById(Long id);

    ResponseEntity<Category> addCategory(Category category, UserDetailsImpl currentUser);

    ResponseEntity<Category> updateCategory(Long id, Category newCategory, UserDetailsImpl currentUser);

    ResponseEntity<?> deleteCategory(Long id, UserDetailsImpl currentUser);
}
