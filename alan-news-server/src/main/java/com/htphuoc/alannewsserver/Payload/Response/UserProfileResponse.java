package com.htphuoc.alannewsserver.Payload.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileResponse {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private Date dob;
    private String gender;
    private String phoneNumber;
    private String email;
    private String address;
    private String avatar;
    private Long postCount;

    public UserProfileResponse(Long id, String username, String firstName, String lastName, Date dob, String gender, String phoneNumber, String email, String address, String avatar, LocalDateTime createdAt, Long postCount) {
    }
}
