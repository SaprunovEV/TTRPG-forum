package by.sapra.ttrpg.forum.domain.exception;

public abstract class DomainException extends RuntimeException {
    public DomainException(String message) {
        super(message);
    }

    public abstract <T> T getData();
}
