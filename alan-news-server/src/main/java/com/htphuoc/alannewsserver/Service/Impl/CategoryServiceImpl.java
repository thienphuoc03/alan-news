package com.htphuoc.alannewsserver.Service.Impl;

import com.htphuoc.alannewsserver.Exception.NotFoundException;
import com.htphuoc.alannewsserver.Exception.SignatureException;
import com.htphuoc.alannewsserver.Model.Category;
import com.htphuoc.alannewsserver.Model.ERole;
import com.htphuoc.alannewsserver.Payload.Response.ApiResponse;
import com.htphuoc.alannewsserver.Payload.Response.PagedResponse;
import com.htphuoc.alannewsserver.Repository.CategoryRepository;
import com.htphuoc.alannewsserver.Security.UserDetailsImpl;
import com.htphuoc.alannewsserver.Service.CategoryService;
import com.htphuoc.alannewsserver.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public PagedResponse<Category> getAllCategory(int page, int size, String sortBy) {
        try {
//            Sort sort = Sort.by(Sort.Direction.fromString(sortName), sortBy);
            Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).descending());
            Page<Category> categories = categoryRepository.findAll(pageable);

            List<Category> content = categories.getNumberOfElements() == 0 ?
                    Collections.emptyList() : categories.getContent();

            return new PagedResponse<>
                    (content, categories.getNumber(), categories.getSize(), categories.getTotalElements(),
                            categories.getTotalPages(), categories.isLast());
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

    @Override
    public ResponseEntity<Category> getCategoryById(Long id) {
        try {
            Category category = categoryRepository.findById(id).orElseThrow(() ->
                    new NotFoundException("Not Found category with id: " + id));

            return new ResponseEntity<>(category, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public ResponseEntity<Category> addCategory(Category category, UserDetailsImpl currentUser) {
        try {
            if (currentUser.getAuthorities().contains(new SimpleGrantedAuthority(ERole.ROLE_ADMIN.toString())) ||
                    currentUser.getAuthorities().contains(new SimpleGrantedAuthority(ERole.ROLE_MODERATOR.toString()))) {
                Category newCategory = categoryRepository.save(category);

                return new ResponseEntity<>(newCategory, HttpStatus.CREATED);
            }
            throw new SignatureException(AppConstants.YOU_DONT_HAVE_PERMISSION_TO + "add category");

        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public ResponseEntity<Category> updateCategory(Long id, Category newCategory, UserDetailsImpl currentUser) {
        try {
            Category category = categoryRepository.findById(id).orElseThrow(() ->
                    new NotFoundException("Not Found category with id: " + id));

            if (currentUser.getAuthorities().contains(new SimpleGrantedAuthority(ERole.ROLE_ADMIN.toString())) ||
                    currentUser.getAuthorities().contains(new SimpleGrantedAuthority(ERole.ROLE_MODERATOR.toString()))) {
                category.setName(newCategory.getName());
                Category updatedCategory = categoryRepository.save(category);

                return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
            }
            throw new SignatureException(AppConstants.YOU_DONT_HAVE_PERMISSION_TO + " edit this category");
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public ResponseEntity<?> deleteCategory(Long id, UserDetailsImpl currentUser) {
        try {
            Category category = categoryRepository.findById(id).orElseThrow(() ->
                    new NotFoundException("Not Found category with id: " + id));

            if (currentUser.getAuthorities().contains(new SimpleGrantedAuthority(ERole.ROLE_ADMIN.toString())) ||
                    currentUser.getAuthorities().contains(new SimpleGrantedAuthority(ERole.ROLE_MODERATOR.toString()))) {
                categoryRepository.deleteById(id);

                return new ResponseEntity<>(new ApiResponse(Boolean.TRUE, "You successfully deleted category"),
                        HttpStatus.OK);
            }
            throw new SignatureException(AppConstants.YOU_DONT_HAVE_PERMISSION_TO + " delete this category");
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}
