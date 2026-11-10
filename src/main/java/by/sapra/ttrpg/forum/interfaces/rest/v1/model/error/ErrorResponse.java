package by.sapra.ttrpg.forum.interfaces.rest.v1.model.error;

import by.sapra.ttrpg.forum.domain.exception.dictionary.ApplicationErrorCodes;

import java.time.Instant;
import java.util.Map;

public record ErrorResponse(
        ApplicationErrorCodes code,
        String message,
        Map<String, Object> data,
        String path,
        Instant timestamp) {

    public static ErrorResponse of(ApplicationErrorCodes code, String message, Map<String, Object> data, String path) {
        return new ErrorResponse(code, message, data == null ? Map.of() : data, path, Instant.now());
    }
}
