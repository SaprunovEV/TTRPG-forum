package by.sapra.ttrpg.forum.domain.exception;

import by.sapra.ttrpg.forum.domain.aggregate.MessageInfo;

public class ParentFromDifferentTopicException extends DomainException {
    public ParentFromDifferentTopicException(MessageInfo messageInfo, MessageInfo parent) {
        super(messageInfo.getMessageId().getBusinessId() + " | " + parent.getMessageId().getBusinessId());
    }
}
