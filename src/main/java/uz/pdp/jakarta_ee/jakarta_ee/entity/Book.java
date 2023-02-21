package uz.pdp.jakarta_ee.jakarta_ee.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Book extends Auditable {
    private String title;
    private String description;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Upload file;

    @Builder(builderMethodName = "childBuilder")
    public Book(String id, LocalDateTime createdAt, LocalDateTime updatedAt, String title, String description, Upload file) {
        super(id, createdAt, updatedAt);
        this.title = title;
        this.description = description;
        this.file = file;
    }
}
