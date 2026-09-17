package by.sapra.ttrpg.forum.application;

import by.sapra.ttrpg.forum.domain.command.AddNewTopicCommand;
import by.sapra.ttrpg.forum.domain.command.CommentTopicCommand;
import by.sapra.ttrpg.forum.domain.query.TopicQuery;
import by.sapra.ttrpg.forum.domain.aggregate.Topic;
import by.sapra.ttrpg.forum.domain.entityObject.Comment;
import by.sapra.ttrpg.forum.domain.entityObject.MessageInfo;
import by.sapra.ttrpg.forum.interfaces.rest.v1.model.FeedQuery;
import by.sapra.ttrpg.forum.domain.command.LikeMessageCommand;

import java.util.List;

public interface TopicService {
    Topic addNewTopic(AddNewTopicCommand addNewTopicCommand);

    Topic findTopicById(TopicQuery aggregate);

    Comment commentMassage(CommentTopicCommand commentTopicCommand);

    List<MessageInfo> getActivityToSubscriber(FeedQuery feedQuery);

    List<MessageInfo> getActivityToOwner(FeedQuery feedQuery);

    void likeMessage(LikeMessageCommand likeMessageCommand);
}
