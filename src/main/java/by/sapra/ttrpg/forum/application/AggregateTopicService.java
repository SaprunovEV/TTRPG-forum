package by.sapra.ttrpg.forum.application;

import by.sapra.ttrpg.forum.application.checker.CategoryExistenceChecker;
import by.sapra.ttrpg.forum.domain.aggregate.MessageId;
import by.sapra.ttrpg.forum.domain.aggregate.MessageInfo;
import by.sapra.ttrpg.forum.domain.command.AddNewTopicCommand;
import by.sapra.ttrpg.forum.domain.command.CommentTopicCommand;
import by.sapra.ttrpg.forum.domain.command.LikeMessageCommand;
import by.sapra.ttrpg.forum.domain.entityObject.Comment;
import by.sapra.ttrpg.forum.domain.entityObject.Topic;
import by.sapra.ttrpg.forum.domain.exception.LikeMessageException;
import by.sapra.ttrpg.forum.domain.exception.ParentCommentNotFoundException;
import by.sapra.ttrpg.forum.domain.exception.TopicNotFoundException;
import by.sapra.ttrpg.forum.domain.query.FeedQuery;
import by.sapra.ttrpg.forum.domain.query.TopicQuery;
import by.sapra.ttrpg.forum.domain.valueObject.MessageType;
import by.sapra.ttrpg.forum.domain.valueObject.User;
import by.sapra.ttrpg.forum.infrastructure.jpa.repositories.MessageInfoRepository;
import by.sapra.ttrpg.forum.infrastructure.jpa.repositories.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AggregateTopicService implements TopicService {
    private final MessageInfoRepository repository;
    private final CommentRepository commentRepository;
    private final CategoryExistenceChecker existenceChecker;
    private final LikeService likeService;
    private final SubscriberService subscriberService;

    @Override
    @Transactional
    public Topic addNewTopic(AddNewTopicCommand command) {
        existenceChecker.ensureExists(command.category());

        return repository.save(new MessageInfo(command)).getTopic();
    }

    @Override
    public Topic findTopicById(TopicQuery aggregate) {
        Optional<MessageInfo> optional = repository.findByMessageIdBusinessId(aggregate.messageId().getBusinessId());
        if (optional.isPresent()) {
            if (!optional.get().getType().equals(MessageType.TOPIC)) {
                throw new TopicNotFoundException(aggregate.messageId());
            }

            Topic topic = optional.get().getTopic();

            List<Comment> comments = commentRepository.findAllByTopic(topic, aggregate.pageable());
            topic.setComments(comments);

            return topic;
        }
        throw new TopicNotFoundException(aggregate.messageId());
    }

    @Override
    @Transactional
    public Comment commentMessage(CommentTopicCommand command) {

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

        MessageInfo userMessage = new MessageInfo(command);

        userMessage.commentTo(command, topic, parent);

        repository.save(userMessage);

        return userMessage.getComment();
    }

    @Override
    public List<MessageInfo> getActivityToSubscriber(FeedQuery feedQuery) {
        List<User> followers = subscriberService.findFollowers(feedQuery.author());
        return repository.findAllByAuthorIn(followers, feedQuery.pageable());
    }

    @Override
    public List<MessageInfo> getActivityToOwner(FeedQuery feedQuery) {
        return repository.findAllByAuthor(feedQuery.author(), feedQuery.pageable());
    }

    @Override
    @Transactional
    public void likeMessage(LikeMessageCommand likeMessageCommand) {
        Optional<MessageInfo> optional = repository.findByMessageIdBusinessId(likeMessageCommand.messageId().getBusinessId());

        optional.ifPresentOrElse(m -> {
            likeService.save(likeMessageCommand.author(), m, likeMessageCommand.like());
        }, () -> {throw new LikeMessageException(likeMessageCommand);});
    }
}
