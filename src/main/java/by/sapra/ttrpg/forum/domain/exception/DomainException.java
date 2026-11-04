package by.sapra.ttrpg.forum.domain.exception;

import java.util.Map;

public abstract class DomainException extends RuntimeException {
    public DomainException(String message) {
        super(message);
    }

    public abstract Map<String, Object> getData();
}
