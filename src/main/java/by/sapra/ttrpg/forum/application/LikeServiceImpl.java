package by.sapra.ttrpg.forum.application;

import by.sapra.ttrpg.forum.domain.aggregate.MessageInfo;
import by.sapra.ttrpg.forum.domain.entityObject.Like;
import by.sapra.ttrpg.forum.domain.entityObject.LikeId;
import by.sapra.ttrpg.forum.domain.valueObject.User;
import by.sapra.ttrpg.forum.infrastructure.jpa.repositories.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {
    private final LikeRepository repository;

    @Override
    public void save(User user, MessageInfo messageInfo, Integer value) {

        Like like = new Like();

        LikeId id = new LikeId();
        id.setUser(user);
        id.setMessageId(messageInfo.getId());

        Optional<Like> byId = repository.findById(id);

        like.setId(id);
        like.setMessage(messageInfo);
        like.setValue(value);

        if (byId.isPresent()) {
            Like fromBase = byId.get();

            if (fromBase.getValue().equals(value)) return;

            repository.save(like);
            return;
        }

        repository.save(like);
    }
}
