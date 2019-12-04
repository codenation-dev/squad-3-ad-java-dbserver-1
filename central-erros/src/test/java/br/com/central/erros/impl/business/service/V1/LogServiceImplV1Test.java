package br.com.central.erros.impl.business.service.V1;

import br.com.central.erros.impl.business.dto.LogDTOV1;
import br.com.central.erros.impl.business.dto.UserDTOV1;
import br.com.central.erros.impl.business.entity.V1.LogV1;
import br.com.central.erros.impl.business.entity.V1.UserV1;
import br.com.central.erros.impl.business.entity.enums.Ambiente;
import br.com.central.erros.impl.business.entity.enums.Level;
import br.com.central.erros.impl.business.entity.enums.TipoUser;
import br.com.central.erros.impl.business.repository.V1.LogRepositoryV1;
import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LogServiceImplV1Test {

    @Mock
    private LogRepositoryV1 logRepositoryV1;

    @InjectMocks
    private LogServiceImplV1 logService;

    @Test
    public void retornaListaDeLogs() {
        final List<LogV1> listaDeLogs = new ArrayList<>();
        final LogV1 log = new LogV1(0,"127.0.0.1", 1L, LocalDate.now(), "", "",
                Ambiente.DEVELOPMENT, Level.DEBUG, new UserV1());
        listaDeLogs.add(log);
        when(logRepositoryV1.findAll()).thenReturn(listaDeLogs);

        List<LogDTOV1> actual = logService.buscarTodosLogs();

        assertThat(actual, contains(
                hasProperty("ip", Matchers.is("127.0.0.1"))
        ));
    }

    @Test
    public void salvaLog() {
        final LogDTOV1 actualDto = new LogDTOV1("127.0.0.1", 1L, LocalDate.now(), "", "",
                Ambiente.DEVELOPMENT, Level.DEBUG, new UserV1());

        final LogDTOV1 expected = new LogDTOV1(actualDto.getIp(), actualDto.getNumeroDeEventos(),
                actualDto.getDataDoErro(), actualDto.getTitulo(), actualDto.getDetalhes(), actualDto.getAmbiente(),
                actualDto.getLevel(), actualDto.getUserV1());

        when(logRepositoryV1.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        LogDTOV1 actual = logService.salvarNovoLog(actualDto);

        Assertions.assertThat(actual).isEqualToComparingFieldByField(expected);
    }
}
