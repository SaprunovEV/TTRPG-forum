package by.sapra.ttrpg.forum.domain.valueObject;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class User {
    @Column(name = "user_id", nullable = false)
    private String userId;
}
