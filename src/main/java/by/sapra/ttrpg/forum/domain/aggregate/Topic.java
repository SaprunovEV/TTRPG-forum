package by.sapra.ttrpg.forum.domain.aggregate;

import by.sapra.ttrpg.forum.domain.entityObject.MessageInfo;
import by.sapra.ttrpg.forum.domain.valueObject.Category;
import by.sapra.ttrpg.forum.domain.valueObject.TopicBody;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "topic", schema = "forum")
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @ToString.Include
    private UUID id;

    @Embedded
    @ToString.Include
    private TopicId topicId;

    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "message_info_id", referencedColumnName = "id", nullable = false, unique = true)
    private MessageInfo messageInfo;

    @Embedded
    @ToString.Include
    private TopicBody body;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Topic topic)) return false;
        return topicId != null && topicId.equals(topic.getTopicId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(topicId);
    }
}
