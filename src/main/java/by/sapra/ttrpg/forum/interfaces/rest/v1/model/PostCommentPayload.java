package by.sapra.ttrpg.forum.interfaces.rest.v1.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PostCommentPayload(
        @NotBlank(message = "Содержимое комментария не может быть пустым")
        @Size(
                min = 1,
                max = 500,
                message = "Комментарий должен содержать от 1 до 5000 символов"
        )
        String content,
        @NotBlank(message = "У родительского комментария должен быть правильный id")
        @Size(
                min = 1,
                max = 50,
                message = "Id должен содержать от {min} до {max} символов"
        )
        String parentId
) {
}
