package by.sapra.ttrpg.forum.domain.exception;

import by.sapra.ttrpg.forum.domain.aggregate.MessageId;
import by.sapra.ttrpg.forum.domain.exception.dictionary.ApplicationErrorCodes;
import org.springframework.http.HttpStatus;

import java.util.Map;

public class TopicNotFoundException extends DomainException {
    private final MessageId topicId;

    public TopicNotFoundException(MessageId topicId) {
        super("Топик с id=%s не найден.".formatted(topicId.getBusinessId()));
        this.topicId = topicId;
    }

    @Override public ApplicationErrorCodes getErrorCode() { return ApplicationErrorCodes.TOPIC_NOT_FOUND; }
    @Override public HttpStatus getHttpStatus() { return HttpStatus.NOT_FOUND; }

    @Override
    public Map<String, Object> getData() {
        return Map.of("topicId", topicId.getBusinessId());
    }
}
