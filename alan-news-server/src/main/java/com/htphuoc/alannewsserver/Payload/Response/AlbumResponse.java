package com.htphuoc.alannewsserver.Payload.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.htphuoc.alannewsserver.Model.Photo;
import com.htphuoc.alannewsserver.Model.User;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AlbumResponse extends AuditableResponse {
    private Long id;
    private String title;
    private User user;
    private List<Photo> photo;

    public List<Photo> getPhoto() {
        return photo == null ? null : new ArrayList<>(photo);
    }

    public void setPhoto(List<Photo> photo) {
        if (photo == null) {
            this.photo = null;
        } else {
            this.photo = Collections.unmodifiableList(photo);
        }
    }
}
