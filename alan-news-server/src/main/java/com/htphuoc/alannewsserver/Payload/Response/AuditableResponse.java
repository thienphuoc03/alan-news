package com.htphuoc.alannewsserver.Payload.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
public abstract class AuditableResponse {
    private Long createdBy;

    private LocalDateTime createdAt;

    private Long modifiedBy;

    private LocalDateTime modifiedAt;
}
