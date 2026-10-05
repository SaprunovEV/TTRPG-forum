package by.sapra.ttrpg.forum.interfaces.rest.v1.mapper;

import by.sapra.ttrpg.forum.domain.aggregate.MessageInfo;
import by.sapra.ttrpg.forum.domain.command.AddNewTopicCommand;
import by.sapra.ttrpg.forum.domain.command.CommentTopicCommand;
import by.sapra.ttrpg.forum.domain.command.LikeMessageCommand;
import by.sapra.ttrpg.forum.domain.entityObject.Comment;
import by.sapra.ttrpg.forum.domain.entityObject.Topic;
import by.sapra.ttrpg.forum.domain.query.FeedQuery;
import by.sapra.ttrpg.forum.domain.query.TopicQuery;
import by.sapra.ttrpg.forum.interfaces.rest.v1.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Mapper
public interface TopicMapper {
    @Mapping(source = "variable.messageId", target = "messageId.businessId")
    TopicQuery variableToQuery(MassageVariable variable, Pageable pageable);

    @Mapping(source = "category.id", target = "category")
    @Mapping(source = "messageInfo.messageId.businessId", target = "topicId")
    @Mapping(source = "body.title", target = "title")
    @Mapping(source = "body.content", target = "content")
    @Mapping(source = "messageInfo.author.userId", target = "authorId")
    TopicResource entityToResource(Topic topicById);

    AddNewTopicCommand payloadToCommand(UserAuthId userId, PostTopicPayload payload);

    CommentTopicCommand payloadToCommand(UserAuthId userId, MassageVariable topicId, PostCommentPayload payload);

    CommentResource entityToResource(Comment comment);

    FeedQuery variableToQuery(UserAuthId userId, Pageable pageable);

    default PageResource<MessageResource> entitiesToPage(List<MessageInfo> activityToUser) {
        List<MessageResource> list = activityToUser.stream().map(this::entityToResource).toList();
        return new PageResource<>(list.size(), list);
    }
    default PageResource<CommentResource> commentEntitiesToPage(List<Comment> activityToUser) {
        List<CommentResource> list = activityToUser.stream().map(this::entityToResource).toList();
        return new PageResource<>(list.size(), list);
    }

    MessageResource entityToResource(MessageInfo messageInfo);

    LikeMessageCommand payloadToCommand(UserAuthId userId, MassageVariable variable, LikePayload payload);
}
