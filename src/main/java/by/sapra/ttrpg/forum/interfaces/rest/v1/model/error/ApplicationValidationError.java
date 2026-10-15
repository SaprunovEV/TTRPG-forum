package by.sapra.ttrpg.forum.interfaces.rest.v1.model.error;

import lombok.Data;

import java.util.Map;

@Data
public class ApplicationValidationError {
    private Map<String, String> fieldError;

    public ApplicationValidationError(Map<String, String> fieldErrors) {
        this.fieldError = fieldErrors;
    }
}
