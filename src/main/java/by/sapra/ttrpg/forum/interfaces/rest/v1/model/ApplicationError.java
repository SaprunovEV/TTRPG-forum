package by.sapra.ttrpg.forum.interfaces.rest.v1.model;

import by.sapra.ttrpg.forum.domain.exception.dictinary.ApplicationErrorCodes;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

/**
 * Универсальный класс для представления ответа, содержащего информацию об ошибке.
 * <p>
 * Используется в {@code @RestControllerAdvice} для единообразного формирования
 * тела HTTP-ответа при возникновении исключений. Сериализуется в JSON.
 * <p>
 * Пример ответа:
 * <pre>{@code
 * {
 *   "code": 404,
 *   "errorCode": "CATEGORY_NOT_FOUND",
 *   "title": "Категория не найдена",
 *   "message": "Категория с id=42 не существует",
 *   "path": "/api/topics",
 *   "timestamp": "2025-01-15T10:30:00Z",
 *   "data": { "categoryId": 42 }
 * }
 * }</pre>
 *
 * @param <T> тип дополнительных контекстных данных, специфичных для конкретной ошибки
 *            (например, {@code Map<String, Object>} с ID сущности или именем constraint).
 *            Может быть {@code Void}, если дополнительные данные не требуются.
 *
 * @see org.springframework.web.bind.annotation.RestControllerAdvice
 * @see org.springframework.web.bind.annotation.ExceptionHandler
 */
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApplicationError<T> {
    private T data;
    private int code;
    private ApplicationErrorCodes errorCode;
    private String title;
    private String message;
    private String path;
    private Instant timestamp;
    private String traceId;
}
