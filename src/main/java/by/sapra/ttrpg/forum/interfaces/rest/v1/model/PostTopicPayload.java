package by.sapra.ttrpg.forum.interfaces.rest.v1.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record PostTopicPayload(
        @NotBlank(message = "Заголовок темы не может быть пустым")
        @Size(
                min = 3,
                max = 255,
                message = "Заголовок должен содержать от 3 до 255 символов"
        )
        String title,
        @NotBlank(message = "Содержимое темы не может быть пустым")
        @Size(
                max = 20000,
                message = "Содержимое темы не должно превышать 20000 символов"
        )
        String content,
        @NotNull(message = "Категория обязательна")
        UUID category
) {
}
