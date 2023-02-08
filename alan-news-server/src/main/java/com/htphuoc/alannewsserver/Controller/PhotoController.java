package com.htphuoc.alannewsserver.Controller;

import com.htphuoc.alannewsserver.Payload.Request.PhotoRequest;
import com.htphuoc.alannewsserver.Payload.Response.ApiResponse;
import com.htphuoc.alannewsserver.Payload.Response.PagedResponse;
import com.htphuoc.alannewsserver.Payload.Response.PhotoResponse;
import com.htphuoc.alannewsserver.Security.CurrentUser;
import com.htphuoc.alannewsserver.Security.UserDetailsImpl;
import com.htphuoc.alannewsserver.Service.PhotoService;
import com.htphuoc.alannewsserver.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/photos")
public class PhotoController {
    @Autowired
    private PhotoService photoService;

    @GetMapping
    public PagedResponse<PhotoResponse> getAllPhotos(
            @RequestParam(name = "page", required = false, defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) Integer page,
            @RequestParam(name = "size", required = false, defaultValue = AppConstants.DEFAULT_PAGE_SIZE) Integer size,
            @RequestParam(name = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY) String sortBy) {
        return photoService.getAllPhotos(page, size, sortBy);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PhotoResponse> getPhotoById(
            @PathVariable(name = "id") Long id) {
        PhotoResponse photoResponse = photoService.getPhotoById(id);

        return new ResponseEntity<>(photoResponse, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<PhotoResponse> addPhoto(
            @Valid @RequestBody PhotoRequest photoRequest,
            @CurrentUser UserDetailsImpl currentUser) {
        PhotoResponse photoResponse = photoService.addPhoto(photoRequest, currentUser);
        return new ResponseEntity<>(photoResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR')")
    public ResponseEntity<PhotoResponse> updatePhoto(
            @PathVariable(name = "id") Long id,
            @Valid @RequestBody PhotoRequest photoRequest, @CurrentUser UserDetailsImpl currentUser) {
        PhotoResponse photoResponse = photoService.updatePhoto(id, photoRequest, currentUser);
        return new ResponseEntity<>(photoResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR')")
    public ResponseEntity<ApiResponse> deletePhoto(
            @PathVariable(name = "id") Long id,
            @CurrentUser UserDetailsImpl currentUser) {
        ApiResponse apiResponse = photoService.deletePhoto(id, currentUser);

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
