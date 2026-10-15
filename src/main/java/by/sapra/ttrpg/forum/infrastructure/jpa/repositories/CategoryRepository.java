package by.sapra.ttrpg.forum.infrastructure.jpa.repositories;

import by.sapra.ttrpg.forum.domain.valueObject.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
}
