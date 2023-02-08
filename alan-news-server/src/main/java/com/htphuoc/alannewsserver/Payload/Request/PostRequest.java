package com.htphuoc.alannewsserver.Payload.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostRequest {
    @NotBlank
    @Size(min = 10)
    private String title;

    @NotBlank
    private String thumbnail;

    @NotBlank
    private String description;

    @NotBlank
    @Size(min = 50)
    private String body;

    @NotNull
    private Long categoryId;
}
