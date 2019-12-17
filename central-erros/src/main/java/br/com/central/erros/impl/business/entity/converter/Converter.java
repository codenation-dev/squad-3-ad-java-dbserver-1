package br.com.central.erros.impl.business.entity.converter;

import br.com.central.erros.impl.business.exception.exceptions.ValidationException;

import java.util.Objects;

abstract class Converter {
    static void throwExceptionIfNull(Object object, String errorMessage) {
        if (Objects.isNull(object)) {
            throw new ValidationException(errorMessage);
        }
    }
}
