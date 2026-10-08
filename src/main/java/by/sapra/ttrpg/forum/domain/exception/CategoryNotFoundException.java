package by.sapra.ttrpg.forum.domain.exception;

import by.sapra.ttrpg.forum.domain.valueObject.Category;
import lombok.Getter;

public class CategoryNotFoundException extends DomainException {
    @Getter
    private Category category;

    public CategoryNotFoundException(Category category) {
        super("Категория с id=%s не найдена".formatted(category.getId()));
        this.category = category;
    }
}
