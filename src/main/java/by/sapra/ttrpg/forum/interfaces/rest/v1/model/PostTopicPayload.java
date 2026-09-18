package by.sapra.ttrpg.forum.interfaces.rest.v1.model;

import java.util.UUID;

public record PostTopicPayload(
        String title,
        String content,
        UUID category
) {
}
