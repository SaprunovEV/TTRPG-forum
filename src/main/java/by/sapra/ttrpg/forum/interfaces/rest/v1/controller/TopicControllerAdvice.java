package by.sapra.ttrpg.forum.interfaces.rest.v1.controller;

import by.sapra.ttrpg.forum.domain.exception.CategoryNotFoundException;
import by.sapra.ttrpg.forum.domain.exception.dictinary.ApplicationErrorCodes;
import by.sapra.ttrpg.forum.interfaces.rest.v1.model.ApplicationError;
import by.sapra.ttrpg.forum.interfaces.rest.v1.model.error.CategoryError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class TopicControllerAdvice {

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ApplicationError<CategoryError>> handleClassNotFoundException(CategoryNotFoundException ex, HttpServletRequest request) {
        ApplicationError<CategoryError> error = ApplicationError.<CategoryError>builder()
                .path(request.getRequestURI())
                .code(404)
                .title("Не найдена категория с id=%s".formatted(ex.getCategory().getId()))
                .message(ex.getMessage())
                .timestamp(Instant.now())
                .traceId("пока нету")
                .errorCode(ApplicationErrorCodes.CATEGORY_NOT_FOUND)
                .data(CategoryError.builder().categoryId(ex.getCategory().getId()).build())
                .build();

        return ResponseEntity.badRequest().body(error);
    }

}
