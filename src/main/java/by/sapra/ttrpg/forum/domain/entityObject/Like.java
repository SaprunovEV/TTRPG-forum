package by.sapra.ttrpg.forum.domain.entityObject;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Entity
@Table(name = "like", schema = "forum")
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
public class Like {
    @EmbeddedId
    private LikeId id;

    @Column(name = "like_value", nullable = false)
    @ToString.Include
    private Integer value;

    @MapsId("messageId")
    @ManyToOne()
    private MessageInfo message;

    @ToString.Include
    public String getUserId() {
        return id != null ? id.getUserId() : null;
    }

    @ToString.Include
    public UUID getMessageId() {
        return id != null ? id.getMessageId() : null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Like like)) return false;
        return id != null && id.equals(like.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
