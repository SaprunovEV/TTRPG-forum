package by.sapra.ttrpg.forum.application;

import by.sapra.ttrpg.forum.application.checker.CategoryExistenceChecker;
import by.sapra.ttrpg.forum.domain.aggregate.MessageId;
import by.sapra.ttrpg.forum.domain.aggregate.MessageInfo;
import by.sapra.ttrpg.forum.domain.command.AddNewTopicCommand;
import by.sapra.ttrpg.forum.domain.command.CommentTopicCommand;
import by.sapra.ttrpg.forum.domain.command.LikeMessageCommand;
import by.sapra.ttrpg.forum.domain.entityObject.Comment;
import by.sapra.ttrpg.forum.domain.entityObject.Topic;
import by.sapra.ttrpg.forum.domain.exception.ParentCommentNotFoundException;
import by.sapra.ttrpg.forum.domain.exception.TopicNotFoundException;
import by.sapra.ttrpg.forum.domain.query.FeedQuery;
import by.sapra.ttrpg.forum.domain.query.TopicQuery;
import by.sapra.ttrpg.forum.infrastructure.jpa.repositories.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    public Comment commentMassage(CommentTopicCommand command) {

        MessageId topicId  = command.topicId();
        MessageId parentId = command.parentId();

        List<MessageId> ids = new ArrayList<>();
        ids.add(topicId);
        if (parentId.getBusinessId() != null) ids.add(parentId);

        Map<MessageId, MessageInfo> found = repository.findAllByBusinessIds(ids);

        MessageInfo topic = found.get(topicId);
        if (topic == null) throw new TopicNotFoundException(topicId);

        MessageInfo parent = null;
        if (parentId.getBusinessId() != null) {
            parent = found.get(parentId);
            if (parent == null) throw new ParentCommentNotFoundException(parentId);
        }

        Comment created = topic.addComment(command.content(), command.author(), parent);

        repository.save(created.getMessageInfo());

        return created;
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
