package by.sapra.ttrpg.forum.infrastructure.jpa.repositories;

import by.sapra.ttrpg.forum.domain.aggregate.MessageId;
import by.sapra.ttrpg.forum.domain.aggregate.MessageInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public interface MessageRepository extends JpaRepository<MessageInfo, UUID> {
    List<MessageInfo> findAllByMessageIdBusinessIdIn(Collection<String> businessIds);

    default Map<MessageId, MessageInfo> findAllByBusinessIds(List<MessageId> ids) {
        List<String> raw = ids.stream().map(MessageId::getBusinessId).toList();
        return findAllByMessageIdBusinessIdIn(raw).stream()
                .collect(Collectors.toMap(
                        MessageInfo::getMessageId,
                        Function.identity()
                ));
    }
}
