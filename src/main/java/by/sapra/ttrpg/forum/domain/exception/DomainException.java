package by.sapra.ttrpg.forum.domain.exception;

import by.sapra.ttrpg.forum.domain.exception.dictinary.ApplicationErrorCodes;
import org.springframework.http.HttpStatus;

import java.util.Map;

public abstract class DomainException extends RuntimeException {
    protected DomainException(String message) {
        super(message);
    }

    /** Код ошибки для клиента (уходит в тело ответа). */
    public abstract ApplicationErrorCodes getErrorCode();

    /** Дополнительные данные об ошибке. Никогда не возвращаем null. */
    public abstract Map<String, Object> getData();

    /** HTTP-статус по умолчанию — 422 Unprocessable Entity. */
    public HttpStatus getHttpStatus() {
        return HttpStatus.UNPROCESSABLE_ENTITY;
    }
}
