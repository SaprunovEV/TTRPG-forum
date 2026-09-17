package by.sapra.ttrpg.forum.domain.query;

import by.sapra.ttrpg.forum.domain.aggregate.TopicId;
import org.springframework.data.domain.Pageable;

public record TopicQuery(TopicId aggregate, Pageable pageable) {
}
