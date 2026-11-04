package by.sapra.ttrpg.forum.domain.exception;

import by.sapra.ttrpg.forum.domain.command.LikeMessageCommand;

public class LikeMessageException extends DomainException {
    private final LikeMessageCommand likeMessageCommand;

    public LikeMessageException(LikeMessageCommand likeMessageCommand) {
        super("");
        this.likeMessageCommand = likeMessageCommand;
    }

    @Override
    public Object getData() {
        return likeMessageCommand;
    }
}
