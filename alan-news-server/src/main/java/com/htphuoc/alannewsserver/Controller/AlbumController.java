package com.htphuoc.alannewsserver.Controller;

import com.htphuoc.alannewsserver.Model.Album;
import com.htphuoc.alannewsserver.Payload.Request.AlbumRequest;
import com.htphuoc.alannewsserver.Payload.Response.AlbumResponse;
import com.htphuoc.alannewsserver.Payload.Response.ApiResponse;
import com.htphuoc.alannewsserver.Payload.Response.PagedResponse;
import com.htphuoc.alannewsserver.Payload.Response.PhotoResponse;
import com.htphuoc.alannewsserver.Security.CurrentUser;
import com.htphuoc.alannewsserver.Security.UserDetailsImpl;
import com.htphuoc.alannewsserver.Service.AlbumService;
import com.htphuoc.alannewsserver.Service.PhotoService;
import com.htphuoc.alannewsserver.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/albums")
public class AlbumController {
    @Autowired
    private AlbumService albumService;

    @Autowired
    private PhotoService photoService;

    @GetMapping
    public PagedResponse<AlbumResponse> getAllAlbums(
            @RequestParam(name = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) Integer page,
            @RequestParam(name = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) Integer size,
            @RequestParam(name = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY) String sortBy) {
        return albumService.getAllAlbums(page, size, sortBy);
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Album> addAlbum(
            @Valid @RequestBody AlbumRequest albumRequest,
            @CurrentUser UserDetailsImpl currentUser) {
        return albumService.addAlbum(albumRequest, currentUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Album> getAlbumById(
            @PathVariable(name = "id") Long id) {
        return albumService.getAlbumById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR')")
    public ResponseEntity<AlbumResponse> updateAlbum(
            @PathVariable(name = "id") Long id,
            @Valid @RequestBody AlbumRequest newAlbum,
            @CurrentUser UserDetailsImpl currentUser) {
        return albumService.updateAlbum(id, newAlbum, currentUser);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR')")
    public ResponseEntity<ApiResponse> deleteAlbum(
            @PathVariable(name = "id") Long id,
            @CurrentUser UserDetailsImpl currentUser) {
        return albumService.deleteAlbum(id, currentUser);
    }

    @GetMapping("/{id}/photos")
    public ResponseEntity<PagedResponse<PhotoResponse>> getAllPhotosByAlbum(
            @PathVariable(name = "id") Long id,
            @RequestParam(name = "page", required = false, defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) Integer page,
            @RequestParam(name = "size", required = false, defaultValue = AppConstants.DEFAULT_PAGE_SIZE) Integer size,
            @RequestParam(name = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY) String sortBy) {

        PagedResponse<PhotoResponse> response = photoService.getAllPhotosByAlbum(id, page, size, sortBy);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
