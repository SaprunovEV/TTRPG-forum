package by.sapra.ttrpg.forum.domain.entityObject;

import by.sapra.ttrpg.forum.domain.valueObject.DateInfo;
import by.sapra.ttrpg.forum.domain.valueObject.User;
import jakarta.persistence.*;
import lombok.*;

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
    @AttributeOverride(name = "userId", column = @Column(name = "author_id", nullable = false))
    @ToString.Include
    private User author;

    @Embedded
    @ToString.Include
    private DateInfo dateInfo = new DateInfo();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MessageInfo mf)) return false;
        return id != null && id.equals(mf.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
