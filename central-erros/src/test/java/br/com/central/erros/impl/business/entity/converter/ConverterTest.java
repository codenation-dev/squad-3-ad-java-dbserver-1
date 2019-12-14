package br.com.central.erros.impl.business.entity.converter;

import br.com.central.erros.impl.business.exception.exceptions.ValidationException;
import org.junit.Test;

public class ConverterTest {
    @Test(expected = ValidationException.class)
    public void deveRetornarExcessaoDeValidacaoParaObjetoNulo() {
        Converter.throwExceptionIfNull(null, "mensagem");
    }
}
