package by.sapra.ttrpg.forum.application;

import by.sapra.ttrpg.forum.application.command.CommentTopicCommand;
import by.sapra.ttrpg.forum.application.command.AddNewTopicCommand;
import by.sapra.ttrpg.forum.application.queries.MeaageInfoQuery;
import by.sapra.ttrpg.forum.application.queries.TopicQuery;
import by.sapra.ttrpg.forum.domain.aggregate.Topic;
import by.sapra.ttrpg.forum.domain.entityObject.Comment;
import by.sapra.ttrpg.forum.domain.entityObject.MessageInfo;

import java.util.List;

public interface TopicService {
    Topic addNewTopic(AddNewTopicCommand addNewTopicCommand);

    Topic findTopicById(TopicQuery aggregate);

    Comment commentMassage(CommentTopicCommand commentTopicCommand);

    List<MessageInfo> findFeedToUser(MeaageInfoQuery meaageInfoQuery);
}
