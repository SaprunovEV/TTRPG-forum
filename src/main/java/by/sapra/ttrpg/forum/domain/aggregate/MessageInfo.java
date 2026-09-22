package by.sapra.ttrpg.forum.domain.aggregate;

import by.sapra.ttrpg.forum.domain.valueObject.DateInfo;
import by.sapra.ttrpg.forum.domain.valueObject.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "message_info", schema = "forum")
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
public class MessageInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @ToString.Include
    private UUID id;

    @Embedded
    @ToString.Include
    private MessageId messageId;

    @Embedded
    @AttributeOverride(name = "userId", column = @Column(name = "author_id", nullable = false))
    @ToString.Include
    private User author;

    @Embedded
    @ToString.Include
    private DateInfo dateInfo = new DateInfo();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MessageInfo messageInfo)) return false;
        return messageId != null && messageId.equals(messageInfo.getMessageId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(messageId);
    }
}
