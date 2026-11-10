package by.sapra.ttrpg.forum.interfaces.rest.v1.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record PostCommentPayload(
        @NotBlank(message = "Содержимое комментария не может быть пустым")
        @Size(
                min = 2,
                max = 500,
                message = "Комментарий должен содержать от {min} до {max} символов"
        )
        String content,

        @Pattern(
                regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$",
                message = "parentId должен быть корректным UUID"
        )
        String parentId
) {
}
