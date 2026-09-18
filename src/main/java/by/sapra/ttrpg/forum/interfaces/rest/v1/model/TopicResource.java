package by.sapra.ttrpg.forum.interfaces.rest.v1.model;

import java.util.UUID;

public record TopicResource(
        String authorId,
        String topicId,
        String title,
        String content,
        UUID category,
        PageResource<CommentResource> comments
) {
}
