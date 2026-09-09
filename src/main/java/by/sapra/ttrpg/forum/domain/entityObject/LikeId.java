package by.sapra.ttrpg.forum.domain.entityObject;

import by.sapra.ttrpg.forum.domain.valueObject.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;
import java.util.UUID;

@Embeddable
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
public class LikeId {
    @Embedded
    @ToString.Include
    private User user;

    private UUID messageId;

    public String getUserId() {
        return user.getUserId();
    }

    @ToString.Include
    public UUID getMessageId() {
        return this.messageId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LikeId that)) return false;
        return Objects.equals(getUserId(), that.getUserId()) &&
                Objects.equals(getMessageId(), that.getMessageId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getMessageId());
    }
}
