package br.com.central.erros.impl.business.entity.converter;

import br.com.central.erros.impl.business.dto.LogDTOV1;
import br.com.central.erros.impl.business.entity.V1.LogV1;
import br.com.central.erros.impl.business.entity.V1.UserV1;
import br.com.central.erros.impl.business.entity.enums.Environment;
import br.com.central.erros.impl.business.entity.enums.Level;
import br.com.central.erros.impl.business.exception.exceptions.ValidationException;
import org.junit.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class LogConverterTest {

    @Test
    public void converteDeDTOParaEntidade() {
        LogV1 expected = new LogV1(null, "127.0.0.1", 2L, LocalDate.now(), "", "",
                Environment.DEVELOPMENT, Level.DEBUG, new UserV1(), true);

        LogDTOV1 dto = new LogDTOV1(expected.getIp(), expected.getNumberOfEvents(), expected.getDate(),
                expected.getTitle(), expected.getDetails(), expected.getActive(), expected.getEnvironment(), expected.getLevel(),
                expected.getUser());

        assertThat(expected).isEqualToComparingFieldByFieldRecursively(LogConverter.logDTOToEntity(dto));
    }

    @Test
    public void converteDeEntidadeParaDTO() {
        LogDTOV1 expected = new LogDTOV1("127.0.0.1", 2L, LocalDate.now(), "", "", true,
                Environment.DEVELOPMENT, Level.DEBUG, new UserV1());

        LogV1 entity = new LogV1(null, expected.getIp(), expected.getNumberOfEvents(), expected.getDate(),
                expected.getTitle(), expected.getDetails(), expected.getEnvironment(), expected.getLevel(),
                expected.getUser(), expected.getActive());

        assertThat(expected).isEqualToComparingFieldByFieldRecursively(LogConverter.logToDTO(entity));
    }

    @Test(expected = ValidationException.class)
    public void retornaExcessaoQuandoTentaConverterLogNulaParaDTO() {
        LogConverter.logToDTO(null);
    }

    @Test(expected = ValidationException.class)
    public void retornaExcessaoQuandoTentaConverterDTONuloParaLog() {
        LogConverter.logDTOToEntity(null);
    }
}
