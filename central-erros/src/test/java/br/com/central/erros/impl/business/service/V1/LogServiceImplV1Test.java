package br.com.central.erros.impl.business.service.V1;

import br.com.central.erros.impl.business.dto.LogDTOV1;
import br.com.central.erros.impl.business.entity.V1.LogV1;
import br.com.central.erros.impl.business.entity.V1.UserV1;
import br.com.central.erros.impl.business.entity.converter.LogConverter;
import br.com.central.erros.impl.business.entity.enums.Environment;
import br.com.central.erros.impl.business.entity.enums.Level;
import br.com.central.erros.impl.business.entity.enums.UserType;
import br.com.central.erros.impl.business.exception.exceptions.ObjectNotFoundException;
import br.com.central.erros.impl.business.repository.V1.LogRepositoryV1;
import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
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
                Environment.DEVELOPMENT, Level.DEBUG, new UserV1());
        listaDeLogs.add(log);
        when(logRepositoryV1.findAll()).thenReturn(listaDeLogs);

        List<LogDTOV1> actual = logService.findAll();

        assertThat(actual, contains(
                hasProperty("ip", Matchers.is("127.0.0.1"))
        ));
    }

    @Test
    public void salvaLog() {
        final LogDTOV1 actualDto = new LogDTOV1("127.0.0.1", 1L, LocalDate.now(), "", "",
                Environment.DEVELOPMENT, Level.DEBUG, new UserV1());

        final LogDTOV1 expected = new LogDTOV1(actualDto.getIp(), actualDto.getNumberOfEvents(),
                actualDto.getDate(), actualDto.getTitle(), actualDto.getDetails(), actualDto.getEnvironment(),
                actualDto.getLevel(), actualDto.getUser());

        when(logRepositoryV1.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        LogDTOV1 actual = logService.save(actualDto);

        Assertions.assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    @Test
    public void encontraLogPorId() {
        final LogDTOV1 expected = new LogDTOV1("127.0.0.1", 1L, LocalDate.now(), "", "",
                Environment.DEVELOPMENT, Level.DEBUG, new UserV1());
        when(logRepositoryV1.findById(1)).thenReturn(Optional.of(LogConverter.logDTOToEntity(expected)));
        final LogDTOV1 actual = logService.findById(1);
        Assertions.assertThat(actual).isEqualToComparingFieldByFieldRecursively(expected);
    }

    @Test(expected = ObjectNotFoundException.class)
    public void retornaExcessaoQuandoLogNaoExiste() {
        when(logRepositoryV1.findById(1)).thenReturn(Optional.empty());
        logService.findById(1);
    }

    @Test
    public void encontraLogsPorUsuario() {
        final UserV1 coletor = new UserV1(0,"João", "joao@123.com",
                "123", UserType.PESSOAFISICA, "$2$546");
        final LogV1 log = new LogV1(0, "", 1L, LocalDate.now(), "Log",
                "",  Environment.DEVELOPMENT, Level.DEBUG, coletor);
        final List<LogV1> listaDeLogs = new ArrayList<>();

        listaDeLogs.add(log);

        when(logRepositoryV1.findAllByUser_Id(1)).thenReturn(listaDeLogs);
        List<LogDTOV1> actual = logService.findByUser(1);
        assertThat(actual, contains(
                hasProperty("titulo", Matchers.is("Log"))
        ));

    }
}
