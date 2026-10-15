package by.sapra.ttrpg.forum.application.checker;

import by.sapra.ttrpg.forum.domain.exception.CategoryNotFoundException;
import by.sapra.ttrpg.forum.domain.valueObject.Category;
import by.sapra.ttrpg.forum.infrastructure.jpa.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryExistenceChecker {
    private final CategoryRepository repository;

    public void ensureExists(Category category) {
        if(!repository.existsById(category.getId())){
            throw new CategoryNotFoundException(category);
        }
    }
}
