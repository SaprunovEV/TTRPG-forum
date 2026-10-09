package by.sapra.ttrpg.forum.domain.exception;

import by.sapra.ttrpg.forum.domain.aggregate.MessageId;

public class ParentCommentNotFoundException extends DomainException {
    public ParentCommentNotFoundException(MessageId parentId) {
        super(parentId.getBusinessId());
    }
}
