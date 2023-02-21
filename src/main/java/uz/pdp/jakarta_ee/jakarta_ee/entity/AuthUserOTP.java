package uz.pdp.jakarta_ee.jakarta_ee.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import lombok.*;

import java.time.LocalDateTime;
import java.time.ZoneId;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AuthUserOTP extends Auditable {

    @Column(nullable = false, updatable = false)
    private String userID;

    @Column(columnDefinition = "timestamp default now() + interval '5 minute'", nullable = false, updatable = false)
    private LocalDateTime validTill;

    private boolean deleted;


    @Builder(builderMethodName = "childBuilder")
    public AuthUserOTP(String id, LocalDateTime createdAt, LocalDateTime updatedAt, String userID, LocalDateTime validTill, boolean deleted) {
        super(id, createdAt, updatedAt);
        this.userID = userID;
        this.validTill = validTill;
        this.deleted = deleted;
    }

    @PrePersist
    public void prePersist() {
        this.validTill = LocalDateTime.now(ZoneId.of("Asia/Tashkent")).plusMinutes(5);
    }


}
