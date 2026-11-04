package by.sapra.ttrpg.forum.domain.exception;

import by.sapra.ttrpg.forum.domain.valueObject.Category;
import lombok.Getter;

import java.util.Map;

public class CategoryNotFoundException extends DomainException {
    @Getter
    private Category category;

    public CategoryNotFoundException(Category category) {
        super("Категория с id=%s не найдена".formatted(category.getId()));
        this.category = category;
    }

    @Override
    public Map<String, Object> getData() {
        return Map.of("categoryId", category.getId());
    }
}
