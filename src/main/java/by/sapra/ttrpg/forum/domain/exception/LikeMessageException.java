package by.sapra.ttrpg.forum.domain.exception;

import by.sapra.ttrpg.forum.domain.command.LikeMessageCommand;

import java.util.Map;

public class LikeMessageException extends DomainException {
    private final LikeMessageCommand likeMessageCommand;

    public LikeMessageException(LikeMessageCommand likeMessageCommand) {
        super("y удалось оставить реакцию пользователя: %s, на сообщения: %s, со значением %d"
                .formatted(likeMessageCommand.author().getUserId(), likeMessageCommand.messageId().getBusinessId(), likeMessageCommand.like()));
        this.likeMessageCommand = likeMessageCommand;
    }

    @Override
    public Map<String, Object> getData() {
        return Map.of(
                "messageId", likeMessageCommand.messageId(),
                "userId", likeMessageCommand.author(),
                "value", likeMessageCommand.like()
        );
    }
}
