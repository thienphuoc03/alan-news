package com.htphuoc.alannewsserver.Service.Impl;

import com.htphuoc.alannewsserver.Exception.NotFoundException;
import com.htphuoc.alannewsserver.Exception.SignatureException;
import com.htphuoc.alannewsserver.Model.Album;
import com.htphuoc.alannewsserver.Model.ERole;
import com.htphuoc.alannewsserver.Model.Photo;
import com.htphuoc.alannewsserver.Payload.Request.PhotoRequest;
import com.htphuoc.alannewsserver.Payload.Response.ApiResponse;
import com.htphuoc.alannewsserver.Payload.Response.PagedResponse;
import com.htphuoc.alannewsserver.Payload.Response.PhotoResponse;
import com.htphuoc.alannewsserver.Repository.AlbumRepository;
import com.htphuoc.alannewsserver.Repository.PhotoRepository;
import com.htphuoc.alannewsserver.Security.UserDetailsImpl;
import com.htphuoc.alannewsserver.Service.PhotoService;
import com.htphuoc.alannewsserver.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class PhotoServiceImpl implements PhotoService {
    @Autowired
    private PhotoRepository photoRepository;

    @Autowired
    private AlbumRepository albumRepository;

    @Override
    public PagedResponse<PhotoResponse> getAllPhotos(Integer page, Integer size, String sortBy) {
        try {
            Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, sortBy);
            Page<Photo> photos = photoRepository.findAll(pageable);

            List<PhotoResponse> photoResponses = new ArrayList<>(photos.getContent().size());
            for (Photo photo : photos.getContent()) {
                photoResponses.add(new PhotoResponse(photo.getId(), photo.getTitle(), photo.getUrl(),
                        photo.getThumbnailUrl(), photo.getAlbum().getId()));
            }

            if (photos.getNumberOfElements() == 0) {
                return new PagedResponse<>(Collections.emptyList(), photos.getNumber(), photos.getSize(),
                        photos.getTotalElements(), photos.getTotalPages(), photos.isLast());
            }
            return new PagedResponse<>(photoResponses, photos.getNumber(), photos.getSize(), photos.getTotalElements(),
                    photos.getTotalPages(), photos.isLast());
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public PhotoResponse getPhotoById(Long id) {
        try {
            Photo photo = photoRepository.findById(id).orElseThrow(() ->
                    new NotFoundException("Not Found photo with id: " + id));

            return new PhotoResponse(photo.getId(), photo.getTitle(), photo.getUrl(),
                    photo.getThumbnailUrl(), photo.getAlbum().getId());
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public PhotoResponse addPhoto(PhotoRequest photoRequest, UserDetailsImpl currentUser) {
        try {
            Album album = albumRepository.findById(photoRequest.getAlbumId()).orElseThrow(() ->
                    new NotFoundException("Not Found album with id: " + photoRequest.getAlbumId()));

            if (album.getUser().getId().equals(currentUser.getId())) {
                Photo photo = new Photo(photoRequest.getTitle(), photoRequest.getUrl(), photoRequest.getThumbnailUrl(),
                        album);
                Photo newPhoto = photoRepository.save(photo);
                return new PhotoResponse(newPhoto.getId(), newPhoto.getTitle(), newPhoto.getUrl(),
                        newPhoto.getThumbnailUrl(), newPhoto.getAlbum().getId());
            }

            throw new SignatureException(AppConstants.YOU_DONT_HAVE_PERMISSION_TO + " add photo in this album");
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public PhotoResponse updatePhoto(Long id, PhotoRequest photoRequest, UserDetailsImpl currentUser) {
        try {
            Album album = albumRepository.findById(photoRequest.getAlbumId()).orElseThrow(() ->
                    new NotFoundException("Not Found album with id: " + photoRequest.getAlbumId()));
            Photo photo = photoRepository.findById(id).orElseThrow(() ->
                    new NotFoundException("Not Found photo with id: " + id));
            if (photo.getAlbum().getUser().getId().equals(currentUser.getId())
                    || currentUser.getAuthorities().contains(new SimpleGrantedAuthority(ERole.ROLE_MODERATOR.toString()))) {
                photo.setTitle(photoRequest.getTitle());
                photo.setThumbnailUrl(photoRequest.getThumbnailUrl());
                photo.setAlbum(album);
                Photo updatedPhoto = photoRepository.save(photo);
                return new PhotoResponse(updatedPhoto.getId(), updatedPhoto.getTitle(),
                        updatedPhoto.getUrl(), updatedPhoto.getThumbnailUrl(), updatedPhoto.getAlbum().getId());
            }

            throw new SignatureException(AppConstants.YOU_DONT_HAVE_PERMISSION_TO + " update this photo");
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public ApiResponse deletePhoto(Long id, UserDetailsImpl currentUser) {
        try {
            Photo photo = photoRepository.findById(id).orElseThrow(() ->
                    new NotFoundException("Not Found album with id: " + id));
            if (photo.getAlbum().getUser().getId().equals(currentUser.getId()) ||
                    currentUser.getAuthorities().contains(new SimpleGrantedAuthority(ERole.ROLE_MODERATOR.toString()))) {
                photoRepository.deleteById(id);
                return new ApiResponse(Boolean.TRUE, "Photo deleted successfully");
            }

            throw new SignatureException(AppConstants.YOU_DONT_HAVE_PERMISSION_TO + " delete this photo");
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public PagedResponse<PhotoResponse> getAllPhotosByAlbum(Long albumId, int page, int size, String sortBy) {
        try {
            Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, sortBy);

            Page<Photo> photos = photoRepository.findByAlbumId(albumId, pageable);

            List<PhotoResponse> photoResponses = new ArrayList<>(photos.getContent().size());
            for (Photo photo : photos.getContent()) {
                photoResponses.add(new PhotoResponse(photo.getId(), photo.getTitle(), photo.getUrl(),
                        photo.getThumbnailUrl(), photo.getAlbum().getId()));
            }

            return new PagedResponse<>(photoResponses, photos.getNumber(), photos.getSize(), photos.getTotalElements(),
                    photos.getTotalPages(), photos.isLast());
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}
