package by.sapra.ttrpg.forum.domain.exception;

import by.sapra.ttrpg.forum.domain.command.LikeMessageCommand;
import by.sapra.ttrpg.forum.domain.exception.dictinary.ApplicationErrorCodes;

import java.util.Map;

public class LikeMessageException extends DomainException {
    private final LikeMessageCommand likeMessageCommand;

    public LikeMessageException(LikeMessageCommand likeMessageCommand) {
        super("Не удалось оставить реакцию пользователя: %s на сообщение: %s со значением %d"
                .formatted(
                        likeMessageCommand.author().getUserId(),
                        likeMessageCommand.messageId().getBusinessId(),
                        likeMessageCommand.like()));
        this.likeMessageCommand = likeMessageCommand;
    }

    @Override public ApplicationErrorCodes getErrorCode() { return ApplicationErrorCodes.LIKE_MESSAGE_FAILED; }

    @Override
    public Map<String, Object> getData() {
        return Map.of(
                "messageId", likeMessageCommand.messageId().getBusinessId(),
                "userId",    likeMessageCommand.author().getUserId(),
                "value",     likeMessageCommand.like()
        );
    }
}
