package by.sapra.ttrpg.forum.interfaces.rest.v1.model;

import jakarta.validation.constraints.NotBlank;

public record MassageVariable(
        @NotBlank(message = "messageId обязателен")
        String massageId
) {
}
