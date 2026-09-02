package by.sapra.ttrpg.forum.domain.entityObject;

import by.sapra.ttrpg.forum.domain.valueObject.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Embeddable
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class LikeId {
    @Embedded
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "message_id", referencedColumnName = "id")
    private MessageInfo messageInfo;

    public String getUserId() {
        return user.getUserId();
    }

    public UUID getMessageId() {
        return messageInfo.getId();
    }
}
