package by.sapra.ttrpg.forum.domain.query;

import by.sapra.ttrpg.forum.domain.aggregate.MessageId;
import org.springframework.data.domain.Pageable;

public record TopicQuery(MessageId aggregate, Pageable pageable) {
}
