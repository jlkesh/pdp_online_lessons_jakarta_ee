package uz.pdp.jakarta_ee.jakarta_ee.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @CreationTimestamp
    @Column(columnDefinition = "timestamp default now()", updatable = false)
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
