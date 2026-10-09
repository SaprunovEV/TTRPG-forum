package by.sapra.ttrpg.forum.domain.entityObject;

import by.sapra.ttrpg.forum.domain.aggregate.MessageId;
import by.sapra.ttrpg.forum.domain.aggregate.MessageInfo;
import by.sapra.ttrpg.forum.domain.valueObject.MessageType;
import by.sapra.ttrpg.forum.domain.valueObject.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Entity
@Table(name = "comment", schema = "forum")
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @ToString.Include
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "message_info_id", referencedColumnName = "id", nullable = false, unique = true)
    private MessageInfo messageInfo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "topic_id", nullable = false)
    private Topic topic;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_comment_id")
    private Comment parentComment;

    @Column(name = "content")
    @ToString.Include
    private String content;

    public static Comment create(String content, User author, MessageInfo topic, MessageInfo parent) {
        Comment result = new Comment();

        MessageInfo self = new MessageInfo();
        self.setComment(result);
        self.setAuthor(author);
        self.setMessageId(new MessageId(UUID.randomUUID().toString()));
        self.setType(MessageType.COMMENT);

        result.setTopic(topic.getTopic());
        result.setParentComment(parent!=null ? parent.getComment() : null);
        result.setContent(content);
        result.setMessageInfo(self);

        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment comment)) return false;
        return id != null && id.equals(comment.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
