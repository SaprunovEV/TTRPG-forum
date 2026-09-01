package by.sapra.ttrpg.forum.domain.entityObject;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "like", schema = "forum")
public class Like {
    @EmbeddedId
    private LikeId id;
    @Column(name = "like_value", nullable = false)
    private Integer value;

    public String getUserId() {
        return id != null ? id.getUserId() : null;
    }

    public Long getMessageId() {
        return id != null ? id.getMessageId() : null;
    }
}
