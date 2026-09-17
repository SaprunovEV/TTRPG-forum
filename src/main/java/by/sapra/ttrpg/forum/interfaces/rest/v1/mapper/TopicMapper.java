package by.sapra.ttrpg.forum.interfaces.rest.v1.mapper;

import by.sapra.ttrpg.forum.domain.command.AddNewTopicCommand;
import by.sapra.ttrpg.forum.domain.command.CommentTopicCommand;
import by.sapra.ttrpg.forum.domain.command.LikeMessageCommand;
import by.sapra.ttrpg.forum.domain.query.TopicQuery;
import by.sapra.ttrpg.forum.domain.aggregate.Topic;
import by.sapra.ttrpg.forum.domain.entityObject.Comment;
import by.sapra.ttrpg.forum.domain.entityObject.MessageInfo;
import by.sapra.ttrpg.forum.interfaces.rest.v1.model.*;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TopicMapper {
    TopicQuery variableToQuery(MassageVariable variable, Pageable pageable);

    TopicResource entityToResource(Topic topicById);

    AddNewTopicCommand payloadToCommand(UserAuthId userId, PostTopicPayload payload);

    CommentTopicCommand payloadToCommand(UserAuthId userId, MassageVariable topicId, PostCommentPayload payload);

    CommentResource entityToResource(Comment comment);

    FeedQuery variableToQuery(UserAuthId userId, Pageable pageable);

    PageResource<MessageResource> entitiesToPage(List<MessageInfo> activityToUser);

    LikeMessageCommand payloadToCommand(UserAuthId userId, MassageVariable variable, LikePayload payload);
}
