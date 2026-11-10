package by.sapra.ttrpg.forum.domain.exception;

import by.sapra.ttrpg.forum.domain.aggregate.MessageId;
import by.sapra.ttrpg.forum.domain.exception.dictionary.ApplicationErrorCodes;
import org.springframework.http.HttpStatus;

import java.util.Map;

public class ParentCommentNotFoundException extends DomainException {
    private final MessageId parentId;

    public ParentCommentNotFoundException(MessageId parentId) {
        super("Родительский комментарий с id=%s не найден.".formatted(parentId.getBusinessId()));
        this.parentId = parentId;
    }

    @Override public ApplicationErrorCodes getErrorCode() { return ApplicationErrorCodes.PARENT_COMMENT_NOT_FOUND; }
    @Override public HttpStatus getHttpStatus() { return HttpStatus.NOT_FOUND; }

    @Override
    public Map<String, Object> getData() {
        return Map.of("parentCommentId", parentId.getBusinessId());
    }
}
