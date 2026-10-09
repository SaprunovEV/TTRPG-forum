package by.sapra.ttrpg.forum.domain.exception;

import by.sapra.ttrpg.forum.domain.aggregate.MessageId;

public class TopicClosedException extends DomainException {
    public TopicClosedException(MessageId messageId) {
        super(messageId.getBusinessId());
    }
}
