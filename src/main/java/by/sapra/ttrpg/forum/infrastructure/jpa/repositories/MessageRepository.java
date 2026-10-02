package by.sapra.ttrpg.forum.infrastructure.jpa.repositories;

import by.sapra.ttrpg.forum.domain.aggregate.MessageInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MessageRepository extends JpaRepository<MessageInfo, UUID> {
}
