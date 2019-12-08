package br.com.central.erros.impl.business.entity.converter;

import br.com.central.erros.impl.business.exception.exceptions.ValidationException;

import java.util.Objects;

abstract class Converter {
    static void jogarExcessaoSeObjetoNulo(Object objeto, String mensagemDeErro) {
        if (Objects.isNull(objeto)) {
            throw new ValidationException(mensagemDeErro);
        }
    }
}
