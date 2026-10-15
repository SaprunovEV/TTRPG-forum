package by.sapra.ttrpg.forum.domain.exception;

import by.sapra.ttrpg.forum.domain.aggregate.MessageId;

public class TopicNotFoundException extends DomainException {
    public TopicNotFoundException(MessageId id) {
        super("Топик с id=%s не найден.".formatted(id.getBusinessId()));
    }

    public TopicNotFoundException(String message) {
        super(message);
    }

    @Override
    public <T> T getData() {
        return null;
    }
}
