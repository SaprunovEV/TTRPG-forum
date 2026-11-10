package by.sapra.ttrpg.forum.infrastructure.jpa.repositories;

import by.sapra.ttrpg.forum.domain.entityObject.Like;
import by.sapra.ttrpg.forum.domain.entityObject.LikeId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, LikeId> {
}
