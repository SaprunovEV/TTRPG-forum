package by.sapra.ttrpg.forum.domain.aggregate;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class MessageId {
    @Column(name = "business_id", nullable = false, unique = true)
    private String businessId;
}
