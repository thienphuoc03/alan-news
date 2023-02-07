package com.htphuoc.alannewsserver.Service;

import com.htphuoc.alannewsserver.Payload.Request.PhotoRequest;
import com.htphuoc.alannewsserver.Payload.Response.ApiResponse;
import com.htphuoc.alannewsserver.Payload.Response.PagedResponse;
import com.htphuoc.alannewsserver.Payload.Response.PhotoResponse;
import com.htphuoc.alannewsserver.Security.UserDetailsImpl;
import org.springframework.stereotype.Component;

@Component
public interface PhotoService {
    PagedResponse<PhotoResponse> getAllPhotos(Integer page, Integer size, String sortBy);

    PhotoResponse getPhotoById(Long id);

    PhotoResponse updatePhoto(Long id, PhotoRequest photoRequest, UserDetailsImpl currentUser);

    PhotoResponse addPhoto(PhotoRequest photoRequest, UserDetailsImpl currentUser);

    ApiResponse deletePhoto(Long id, UserDetailsImpl currentUser);

    PagedResponse<PhotoResponse> getAllPhotosByAlbum(Long albumId, int page, int size, String sortBy);
}
