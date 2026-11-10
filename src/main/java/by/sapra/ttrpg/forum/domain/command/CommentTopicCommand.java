package by.sapra.ttrpg.forum.domain.command;

import by.sapra.ttrpg.forum.domain.aggregate.MessageId;
import by.sapra.ttrpg.forum.domain.valueObject.User;

public record CommentTopicCommand(
        User author,
        MessageId topicId,
        MessageId parentId,
        String content) {
}
