package by.sapra.ttrpg.forum.interfaces.rest.v1.controller;

import by.sapra.ttrpg.forum.domain.exception.DomainException;
import by.sapra.ttrpg.forum.domain.exception.dictinary.ApplicationErrorCodes;
import by.sapra.ttrpg.forum.interfaces.rest.v1.model.ApplicationError;
import by.sapra.ttrpg.forum.interfaces.rest.v1.model.error.ApplicationValidationError;
import by.sapra.ttrpg.forum.interfaces.rest.v1.model.error.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class TopicControllerAdvice {

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<ErrorResponse> handleDomainException(DomainException ex, HttpServletRequest request) {
        log.warn("Domain error: code={}, message={}, data={}",
                ex.getErrorCode(), ex.getMessage(), ex.getData(), ex);

        return ResponseEntity
                .status(ex.getHttpStatus())
                .body(ErrorResponse.of(ex.getErrorCode(), ex.getMessage(), ex.getData()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApplicationError<ApplicationValidationError>> handleBodyValidation(
            MethodArgumentNotValidException ex,
            HttpServletRequest request) {

        Map<String, String> fieldErrors = new LinkedHashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(fe ->
                fieldErrors.putIfAbsent(fe.getField(), fe.getDefaultMessage())
        );

        ex.getBindingResult().getGlobalErrors().forEach(ge ->
                fieldErrors.putIfAbsent(ge.getObjectName(), ge.getDefaultMessage())
        );

        ApplicationError<ApplicationValidationError> body = ApplicationError.<ApplicationValidationError>builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .errorCode(ApplicationErrorCodes.VALIDATION_ERROR)
                .title("Ошибка валидации")
                .message("Запрос содержит некорректные поля")
                .path(request.getRequestURI())
                .timestamp(Instant.now())
                .traceId("пока нету")
                .data(new ApplicationValidationError(fieldErrors))
                .build();

        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApplicationError<ApplicationValidationError>> handleParameterValidation(
            ConstraintViolationException ex,
            HttpServletRequest request) {

        Map<String, String> fieldErrors = ex.getConstraintViolations().stream()
                .collect(Collectors.toMap(
                        this::lastNode,                       // имя поля/параметра
                        ConstraintViolation::getMessage,
                        (existing, replacement) -> existing,
                        LinkedHashMap::new
                ));

        ApplicationError<ApplicationValidationError> body = ApplicationError.<ApplicationValidationError>builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .errorCode(ApplicationErrorCodes.VALIDATION_ERROR)
                .title("Ошибка валидации")
                .message("Запрос содержит некорректные параметры")
                .path(request.getRequestURI())
                .timestamp(Instant.now())
                .traceId("пока нету")
                .data(new ApplicationValidationError(fieldErrors))
                .build();

        return ResponseEntity.badRequest().body(body);
    }

    private String lastNode(ConstraintViolation<?> cv) {
        String path = cv.getPropertyPath().toString();
        int lastDot = path.lastIndexOf('.');
        return lastDot >= 0 ? path.substring(lastDot + 1) : path;
    }
}
