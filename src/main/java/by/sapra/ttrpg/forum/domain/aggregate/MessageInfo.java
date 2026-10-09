package by.sapra.ttrpg.forum.domain.aggregate;

import by.sapra.ttrpg.forum.domain.command.AddNewTopicCommand;
import by.sapra.ttrpg.forum.domain.entityObject.Comment;
import by.sapra.ttrpg.forum.domain.entityObject.Topic;
import by.sapra.ttrpg.forum.domain.exception.InvalidParentException;
import by.sapra.ttrpg.forum.domain.exception.ParentFromDifferentTopicException;
import by.sapra.ttrpg.forum.domain.exception.TopicClosedException;
import by.sapra.ttrpg.forum.domain.valueObject.DateInfo;
import by.sapra.ttrpg.forum.domain.valueObject.MessageType;
import by.sapra.ttrpg.forum.domain.valueObject.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "message_info", schema = "forum")
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@NoArgsConstructor
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

    @OneToOne(mappedBy = "messageInfo", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Topic topic;

    @OneToOne(mappedBy = "messageInfo", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Comment comment;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, length = 20)
    private MessageType type;

    public MessageInfo(AddNewTopicCommand command) {
        this.messageId = generateAggregateId();
        this.author = command.author();
        this.topic = new Topic(command, this);
        this.type = MessageType.TOPIC;
    }

    private MessageId generateAggregateId() {
        return new MessageId(UUID.randomUUID().toString());
    }

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

    public Comment addComment(String content, User author, MessageInfo parent) {
        if (parent != null && parent.type != MessageType.COMMENT)
            throw new InvalidParentException(parent);

        if (parent != null && !parent.comment.getTopic().getId().equals(this.topic.getId())) {
            throw new ParentFromDifferentTopicException(this, parent);
        }

        if (!this.isOpenForComments()) { //пока не реализована Todo
            throw new TopicClosedException(this.getMessageId());
        }
        return Comment.create(content, author, this, parent);
    }

    private boolean isOpenForComments() {
        return true;
    }
}
