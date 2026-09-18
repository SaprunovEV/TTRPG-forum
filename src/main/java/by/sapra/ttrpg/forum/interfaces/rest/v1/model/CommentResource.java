package by.sapra.ttrpg.forum.interfaces.rest.v1.model;

import java.time.Instant;
import java.util.UUID;

public record CommentResource(
        String commentId,
        String authorId,
        UUID parent,
        Instant date,
        String content
) {
}
