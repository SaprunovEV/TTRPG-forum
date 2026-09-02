package by.sapra.ttrpg.forum.domain.entityObject;

import by.sapra.ttrpg.forum.domain.valueObject.User;
import by.sapra.ttrpg.forum.domain.valueObject.DateInfo;
import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
@Entity
@Table(name = "message_info", schema = "forum")
public class MessageInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Embedded
    @AttributeOverride(name = "userId", column = @Column(name = "author_id", nullable = false))
    private User author;

    @Embedded
    private DateInfo dateInfo;

    @PrePersist
    public void create() {
        Instant now = Instant.now();
        dateInfo = new DateInfo(now, now);
    }

    @PreUpdate
    public void update() {
        dateInfo.setUpdateAt(Instant.now());
    }
}
