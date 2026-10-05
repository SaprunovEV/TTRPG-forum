package by.sapra.ttrpg.forum.interfaces.rest.v1.model;

import java.time.Instant;

public record MessageResource(
        String messageId,
        String authorId,
        MassageType type,
        Instant date
) {
}
