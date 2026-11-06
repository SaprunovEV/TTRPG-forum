package by.sapra.ttrpg.forum.domain.exception;

import by.sapra.ttrpg.forum.domain.exception.dictinary.ApplicationErrorCodes;
import org.springframework.http.HttpStatus;

import java.util.Map;

public abstract class DomainException extends RuntimeException {
    protected DomainException(String message) {
        super(message);
    }

    public abstract ApplicationErrorCodes getErrorCode();

    public abstract Map<String, Object> getData();

    public HttpStatus getHttpStatus() {
        return HttpStatus.UNPROCESSABLE_CONTENT;
    }
}
