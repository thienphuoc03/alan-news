package com.htphuoc.alannewsserver.Payload.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {
    private String title;
    private String thumbnail;
    private String description;
    private String body;
    private String category;
}
