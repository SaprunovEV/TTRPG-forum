package by.sapra.ttrpg.forum.domain.command;

import by.sapra.ttrpg.forum.domain.valueObject.Category;
import by.sapra.ttrpg.forum.domain.valueObject.TopicBody;
import by.sapra.ttrpg.forum.domain.valueObject.User;

public record AddNewTopicCommand(TopicBody body, Category category, User author) {

}
