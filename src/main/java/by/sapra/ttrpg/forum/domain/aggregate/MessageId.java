package by.sapra.ttrpg.forum.domain.aggregate;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
public class MessageId {
    @Column(name = "business_id", nullable = false, unique = true)
    private String businessId;

    public MessageId(String id) {
        this.businessId = id;
    }
}
