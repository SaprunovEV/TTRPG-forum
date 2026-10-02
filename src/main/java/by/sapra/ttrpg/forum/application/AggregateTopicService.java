package by.sapra.ttrpg.forum.application;

import by.sapra.ttrpg.forum.domain.aggregate.MessageInfo;
import by.sapra.ttrpg.forum.domain.command.AddNewTopicCommand;
import by.sapra.ttrpg.forum.domain.command.CommentTopicCommand;
import by.sapra.ttrpg.forum.domain.command.LikeMessageCommand;
import by.sapra.ttrpg.forum.domain.entityObject.Comment;
import by.sapra.ttrpg.forum.domain.entityObject.Topic;
import by.sapra.ttrpg.forum.domain.query.FeedQuery;
import by.sapra.ttrpg.forum.domain.query.TopicQuery;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AggregateTopicService implements TopicService {
    @Override
    public Topic addNewTopic(AddNewTopicCommand addNewTopicCommand) {
        return null;
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
