package by.sapra.ttrpg.forum.domain.command;

import by.sapra.ttrpg.forum.domain.aggregate.MessageId;
import by.sapra.ttrpg.forum.domain.valueObject.User;

public record LikeMessageCommand(User author, MessageId messageId, Integer like) {
}
