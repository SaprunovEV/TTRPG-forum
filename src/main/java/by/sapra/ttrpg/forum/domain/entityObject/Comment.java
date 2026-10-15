package by.sapra.ttrpg.forum.domain.entityObject;

import by.sapra.ttrpg.forum.domain.aggregate.MessageInfo;
import by.sapra.ttrpg.forum.domain.command.CommentTopicCommand;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Entity
@Table(name = "comment", schema = "forum")
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@NoArgsConstructor
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

    public Comment(CommentTopicCommand command, MessageInfo messageInfo) {
        this.content = command.content();
        this.messageInfo = messageInfo;
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

    public void commentTo(Topic topic, Comment parentComment) {
        this.parentComment = parentComment;
        this.topic = topic;
    }
}
