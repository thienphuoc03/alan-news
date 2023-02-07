package com.htphuoc.alannewsserver.Service;

import com.htphuoc.alannewsserver.Model.Album;
import com.htphuoc.alannewsserver.Payload.Request.AlbumRequest;
import com.htphuoc.alannewsserver.Payload.Response.AlbumResponse;
import com.htphuoc.alannewsserver.Payload.Response.ApiResponse;
import com.htphuoc.alannewsserver.Payload.Response.PagedResponse;
import com.htphuoc.alannewsserver.Security.UserDetailsImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public interface AlbumService {
    PagedResponse<AlbumResponse> getAllAlbums(Integer page, Integer size, String sortBy);

    ResponseEntity<Album> addAlbum(AlbumRequest albumRequest, UserDetailsImpl currentUser);

    ResponseEntity<Album> getAlbumById(Long id);

    ResponseEntity<AlbumResponse> updateAlbum(Long id, AlbumRequest newAlbum, UserDetailsImpl currentUser);

    ResponseEntity<ApiResponse> deleteAlbum(Long id, UserDetailsImpl currentUser);

    PagedResponse<Album> getUserAlbums(String username, int page, int size, String sortBy);
}
