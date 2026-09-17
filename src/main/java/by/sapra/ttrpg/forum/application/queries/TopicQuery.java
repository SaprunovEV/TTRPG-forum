package by.sapra.ttrpg.forum.application.queries;

import by.sapra.ttrpg.forum.domain.aggregate.TopicId;
import org.springframework.data.domain.Pageable;

public record TopicQuery(TopicId aggregate, Pageable pageable) {
}
