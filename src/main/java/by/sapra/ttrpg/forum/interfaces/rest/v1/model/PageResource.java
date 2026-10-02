package by.sapra.ttrpg.forum.interfaces.rest.v1.model;

import java.util.List;

public record PageResource<T>(
        Integer size,
        List<T> data
) {
}
