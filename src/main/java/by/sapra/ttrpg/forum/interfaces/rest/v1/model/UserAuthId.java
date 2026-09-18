package by.sapra.ttrpg.forum.interfaces.rest.v1.model;

import jakarta.validation.constraints.NotBlank;

public record UserAuthId(
        @NotBlank(message = "messageId обязателен")
        String userId
) {
}
