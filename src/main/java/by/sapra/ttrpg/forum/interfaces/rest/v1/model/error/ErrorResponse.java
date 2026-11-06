package by.sapra.ttrpg.forum.interfaces.rest.v1.model.error;

import by.sapra.ttrpg.forum.domain.exception.dictinary.ApplicationErrorCodes;

import java.time.Instant;
import java.util.Map;

public record ErrorResponse(
        ApplicationErrorCodes code,
        String message,
        Map<String, Object> data,
        Instant timestamp) {

    public static ErrorResponse of(ApplicationErrorCodes code, String message, Map<String, Object> data) {
        return new ErrorResponse(code, message, data == null ? Map.of() : data, Instant.now());
    }
}
