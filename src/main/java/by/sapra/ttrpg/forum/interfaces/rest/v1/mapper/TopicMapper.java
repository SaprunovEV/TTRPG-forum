package by.sapra.ttrpg.forum.interfaces.rest.v1.mapper;

import by.sapra.ttrpg.forum.domain.aggregate.MessageInfo;
import by.sapra.ttrpg.forum.domain.command.AddNewTopicCommand;
import by.sapra.ttrpg.forum.domain.command.CommentTopicCommand;
import by.sapra.ttrpg.forum.domain.command.LikeMessageCommand;
import by.sapra.ttrpg.forum.domain.entityObject.Comment;
import by.sapra.ttrpg.forum.domain.entityObject.Topic;
import by.sapra.ttrpg.forum.domain.query.FeedQuery;
import by.sapra.ttrpg.forum.domain.query.TopicQuery;
import by.sapra.ttrpg.forum.domain.valueObject.Category;
import by.sapra.ttrpg.forum.interfaces.rest.v1.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

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

    @Mapping(source = "userId.userId", target = "author.userId")
    @Mapping(source = "payload.title", target = "body.title")
    @Mapping(source = "payload.content", target = "body.content")
    AddNewTopicCommand payloadToCommand(UserAuthId userId, PostTopicPayload payload);

    @Mapping(source = "authorId.userId", target = "author.userId")
    @Mapping(source = "topicId.messageId", target = "topicId.businessId")
    CommentTopicCommand payloadToCommand(UserAuthId authorId, MassageVariable topicId, PostCommentPayload payload);

    @Mapping(source = "topic.id", target = "topicId")
    @Mapping(source = "id", target = "commentId")
    @Mapping(source = "content", target = "content")
    @Mapping(source = "parentComment.id", target = "parent")
    @Mapping(source = "messageInfo.author.userId", target = "authorId")
    @Mapping(source = "messageInfo.dateInfo.createAt", target = "date")
    CommentResource entityToResource(Comment comment);

    @Mapping(source = "userId.userId", target = "author.userId")
    FeedQuery variableToQuery(UserAuthId userId, Pageable pageable);

    @Mapping(source = "dateInfo.createAt", target = "date")
    @Mapping(source = "messageId.businessId", target = "messageId")
    @Mapping(source = "author.userId", target = "authorId")
    MessageResource entityToResource(MessageInfo messageInfo);

    @Mapping(source = "authorId.userId", target = "author.userId")
    @Mapping(source = "variable.messageId", target = "messageId.businessId")
    @Mapping(source = "payload.value", target = "like")
    LikeMessageCommand payloadToCommand(UserAuthId authorId, MassageVariable variable, LikePayload payload);

    default Category map(UUID id) {
        if (id == null) return null;

        Category category = new Category();
        category.setId(id);
        return category;
    }

    default PageResource<CommentResource> commentEntitiesToPage(List<Comment> activityToUser) {
        List<CommentResource> list = activityToUser.stream().map(this::entityToResource).toList();
        return new PageResource<>(list.size(), list);
    }

    default PageResource<MessageResource> entitiesToPage(List<MessageInfo> activityToUser) {
        List<MessageResource> list = activityToUser.stream().map(this::entityToResource).toList();
        return new PageResource<>(list.size(), list);
    }
}
