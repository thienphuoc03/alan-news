package com.htphuoc.alannewsserver.Service.Impl;

import com.htphuoc.alannewsserver.Exception.NotFoundException;
import com.htphuoc.alannewsserver.Exception.SignatureException;
import com.htphuoc.alannewsserver.Model.Album;
import com.htphuoc.alannewsserver.Model.ERole;
import com.htphuoc.alannewsserver.Model.User;
import com.htphuoc.alannewsserver.Payload.Request.AlbumRequest;
import com.htphuoc.alannewsserver.Payload.Response.AlbumResponse;
import com.htphuoc.alannewsserver.Payload.Response.ApiResponse;
import com.htphuoc.alannewsserver.Payload.Response.PagedResponse;
import com.htphuoc.alannewsserver.Repository.AlbumRepository;
import com.htphuoc.alannewsserver.Repository.UserRepository;
import com.htphuoc.alannewsserver.Security.UserDetailsImpl;
import com.htphuoc.alannewsserver.Service.AlbumService;
import com.htphuoc.alannewsserver.utils.AppConstants;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PagedResponse<AlbumResponse> getAllAlbums(Integer page, Integer size, String sortBy) {
        try {
            Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, sortBy);
            Page<Album> albums = albumRepository.findAll(pageable);

            if (albums.getNumberOfElements() == 0) {
                return new PagedResponse<>(Collections.emptyList(), albums.getNumber(), albums.getSize(), albums.getTotalElements(),
                        albums.getTotalPages(), albums.isLast());
            }
            List<AlbumResponse> albumResponses = Arrays.asList(modelMapper.map(albums.getContent(), AlbumResponse[].class));

            return new PagedResponse<>(albumResponses, albums.getNumber(), albums.getSize(), albums.getTotalElements(), albums.getTotalPages(),
                    albums.isLast());
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public ResponseEntity<Album> addAlbum(AlbumRequest albumRequest, UserDetailsImpl currentUser) {
        try {
            User user = userRepository.getUser(currentUser);
            Album album = new Album();
            modelMapper.map(albumRequest, album);
            album.setUser(user);
            Album newAlbum = albumRepository.save(album);

            return new ResponseEntity<>(newAlbum, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public ResponseEntity<Album> getAlbumById(Long id) {
        try {
            Album album = albumRepository.findById(id).orElseThrow(() ->
                    new NotFoundException("Not found album with id: " + id));

            return new ResponseEntity<>(album, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public ResponseEntity<AlbumResponse> updateAlbum(Long id, AlbumRequest newAlbum, UserDetailsImpl currentUser) {
        try {
            Album album = albumRepository.findById(id).orElseThrow(() ->
                    new NotFoundException("Not found album with id: " + id));
            User user = userRepository.getUser(currentUser);
            if (album.getUser().getId().equals(user.getId()) || currentUser.getAuthorities()
                    .contains(new SimpleGrantedAuthority(ERole.ROLE_MODERATOR.toString()))) {
                album.setTitle(newAlbum.getTitle());
                Album updatedAlbum = albumRepository.save(album);
                AlbumResponse albumResponse = new AlbumResponse();
                modelMapper.map(updatedAlbum, albumResponse);

                return new ResponseEntity<>(albumResponse, HttpStatus.OK);
            }

            throw new SignatureException(AppConstants.YOU_DONT_HAVE_PERMISSION_TO + " update this album");
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public ResponseEntity<ApiResponse> deleteAlbum(Long id, UserDetailsImpl currentUser) {
        try {
            Album album = albumRepository.findById(id).orElseThrow(() ->
                    new NotFoundException("Not found album with id: " + id));
            User user = userRepository.getUser(currentUser);
            if (album.getUser().getId().equals(user.getId()) || currentUser.getAuthorities()
                    .contains(new SimpleGrantedAuthority(ERole.ROLE_MODERATOR.toString()))) {
                albumRepository.deleteById(id);

                return new ResponseEntity<>(new ApiResponse(Boolean.TRUE, "You successfully deleted album"),
                        HttpStatus.OK);
            }

            throw new SignatureException(AppConstants.YOU_DONT_HAVE_PERMISSION_TO + " delete this album");
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public PagedResponse<Album> getUserAlbums(String username, int page, int size, String sortBy) {
        User user = userRepository.getUserByUserName(username);

        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, sortBy);

        Page<Album> albums = albumRepository.findByCreatedBy(user.getId(), pageable);

        List<Album> content = albums.getNumberOfElements() > 0 ? albums.getContent() : Collections.emptyList();

        return new PagedResponse<>(content, albums.getNumber(), albums.getSize(), albums.getTotalElements(), albums.getTotalPages(), albums.isLast());
    }
}
