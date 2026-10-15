package by.sapra.ttrpg.forum.application;

import by.sapra.ttrpg.forum.domain.aggregate.MessageInfo;
import by.sapra.ttrpg.forum.domain.valueObject.User;

public interface LikeService {
    void save(User user, MessageInfo messageInfo, Integer like);
}
