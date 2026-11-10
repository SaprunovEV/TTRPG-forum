package by.sapra.ttrpg.forum.domain.exception;

import by.sapra.ttrpg.forum.domain.exception.dictionary.ApplicationErrorCodes;
import by.sapra.ttrpg.forum.domain.valueObject.Category;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Map;

public class CategoryNotFoundException extends DomainException {
    @Getter
    private final Category category;

    public CategoryNotFoundException(Category category) {
        super("Категория с id=%s не найдена".formatted(category.getId()));
        this.category = category;
    }

    @Override public ApplicationErrorCodes getErrorCode() { return ApplicationErrorCodes.CATEGORY_NOT_FOUND; }
    @Override public HttpStatus getHttpStatus() { return HttpStatus.NOT_FOUND; }

    @Override
    public Map<String, Object> getData() {
        return Map.of("categoryId", category.getId());
    }
}
