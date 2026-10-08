package by.sapra.ttrpg.forum.interfaces.rest.v1.model.error;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class CategoryError {
    private UUID categoryId;
}
