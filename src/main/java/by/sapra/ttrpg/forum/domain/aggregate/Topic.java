package by.sapra.ttrpg.forum.domain.aggregate;

import by.sapra.ttrpg.forum.domain.entityObject.MessageInfo;
import by.sapra.ttrpg.forum.domain.valueObject.Category;
import by.sapra.ttrpg.forum.domain.valueObject.TitleBody;
import jakarta.persistence.*;

@Entity
@Table(name = "topic", schema = "forum")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private TopicId topicId;

    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "message_info_id", referencedColumnName = "id", nullable = false, unique = true)
    private MessageInfo messageInfo;

    @Embedded
    private TitleBody body;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
