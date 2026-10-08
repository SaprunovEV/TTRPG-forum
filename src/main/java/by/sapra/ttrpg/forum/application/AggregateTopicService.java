package by.sapra.ttrpg.forum.application;

import by.sapra.ttrpg.forum.application.checker.CategoryExistenceChecker;
import by.sapra.ttrpg.forum.domain.aggregate.MessageInfo;
import by.sapra.ttrpg.forum.domain.command.AddNewTopicCommand;
import by.sapra.ttrpg.forum.domain.command.CommentTopicCommand;
import by.sapra.ttrpg.forum.domain.command.LikeMessageCommand;
import by.sapra.ttrpg.forum.domain.entityObject.Comment;
import by.sapra.ttrpg.forum.domain.entityObject.Topic;
import by.sapra.ttrpg.forum.domain.query.FeedQuery;
import by.sapra.ttrpg.forum.domain.query.TopicQuery;
import by.sapra.ttrpg.forum.infrastructure.jpa.repositories.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AggregateTopicService implements TopicService {
    private final MessageRepository repository;
    private final CategoryExistenceChecker existenceChecker;

    @Override
    public Topic addNewTopic(AddNewTopicCommand command) {
        existenceChecker.ensureExists(command.category());

        return repository.save(new MessageInfo(command)).getTopic();
    }

    @Override
    public Topic findTopicById(TopicQuery aggregate) {
        return null;
    }

    @Override
    public Comment commentMassage(CommentTopicCommand commentTopicCommand) {
        return null;
    }

    @Override
    public List<MessageInfo> getActivityToSubscriber(FeedQuery feedQuery) {
        return List.of();
    }

    @Override
    public List<MessageInfo> getActivityToOwner(FeedQuery feedQuery) {
        return List.of();
    }

    @Override
    public void likeMessage(LikeMessageCommand likeMessageCommand) {

    }
}
