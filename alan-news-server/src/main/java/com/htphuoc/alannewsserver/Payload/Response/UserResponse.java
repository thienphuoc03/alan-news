package com.htphuoc.alannewsserver.Payload.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse extends AuditableResponse {
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
    private Boolean isActive;

}
