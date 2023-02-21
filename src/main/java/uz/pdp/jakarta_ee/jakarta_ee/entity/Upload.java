package uz.pdp.jakarta_ee.jakarta_ee.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Upload extends Auditable {

    @Column(nullable = false)
    private String generatedName;
    @Column(nullable = false)
    private String originalName;
    @Column(nullable = false)
    private String mimeType;
    private long size;
    @Column(nullable = false)
    private String extension;

    @Builder(builderMethodName = "childBuilder")
    public Upload(String id, LocalDateTime createdAt, LocalDateTime updatedAt, String generatedName, String originalName, String mimeType, long size, String extension) {
        super(id, createdAt, updatedAt);
        this.generatedName = generatedName;
        this.originalName = originalName;
        this.mimeType = mimeType;
        this.size = size;
        this.extension = extension;
    }
}
