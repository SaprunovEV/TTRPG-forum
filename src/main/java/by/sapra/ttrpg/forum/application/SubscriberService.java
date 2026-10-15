package by.sapra.ttrpg.forum.application;

import by.sapra.ttrpg.forum.domain.valueObject.User;

import java.util.List;

public interface SubscriberService {
    List<User> findFollowers(User author);
}
