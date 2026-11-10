package by.sapra.ttrpg.forum.application;

import by.sapra.ttrpg.forum.domain.valueObject.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriberServiceImpl implements SubscriberService {
    @Override
    public List<User> findFollowers(User author) {
        User e1 = new User();
        e1.setUserId("12332112331");
        return List.of(e1);
    }
}
