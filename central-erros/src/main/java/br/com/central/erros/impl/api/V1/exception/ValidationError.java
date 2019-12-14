package br.com.central.erros.impl.api.V1.exception;

import java.util.ArrayList;
import java.util.List;

class ValidationError extends StandardError{

    private static final long serialVersionUID = 1L;

    private final List<FieldMessage> errors = new ArrayList<>();

    public ValidationError(Long timestamp, Integer status, String error, String path) {
        super(timestamp, status, error, path);
    }

    public List<FieldMessage> getErrors() {
        return errors;
    }

    public void addError(String fieldName, String messagem) {
        errors.add(new FieldMessage(fieldName, messagem));
    }
}
