package by.sapra.ttrpg.forum.domain.query;

import by.sapra.ttrpg.forum.domain.valueObject.User;
import org.springframework.data.domain.Pageable;

public record FeedQuery(User author, Pageable pageable) {
}
