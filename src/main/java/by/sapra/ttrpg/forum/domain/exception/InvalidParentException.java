package by.sapra.ttrpg.forum.domain.exception;

import by.sapra.ttrpg.forum.domain.aggregate.MessageInfo;

public class InvalidParentException extends DomainException {
    public InvalidParentException(MessageInfo parent) {
        super(parent.getMessageId().getBusinessId());
    }
}
