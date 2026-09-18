package by.sapra.ttrpg.forum.interfaces.rest.v1.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record LikePayload(
        @NotNull(message = "Значение реакции обязательно")
        @Min(value = -1, message = "Значение должно быть -1, 0 или 1")
        @Max(value = 1, message = "Значение должно быть -1, 0 или 1")
        Integer value
) {
}
