package br.com.central.erros.impl.business.service.V1;

import br.com.central.erros.impl.business.dto.LogDTOV1;
import br.com.central.erros.impl.business.entity.V1.LogV1;
import br.com.central.erros.impl.business.entity.V1.UserV1;
import br.com.central.erros.impl.business.entity.converter.LogConverter;
import br.com.central.erros.impl.business.entity.enums.Environment;
import br.com.central.erros.impl.business.entity.enums.FindBy;
import br.com.central.erros.impl.business.entity.enums.Level;
import br.com.central.erros.impl.business.entity.enums.UserType;
import br.com.central.erros.impl.business.exception.exceptions.ObjectNotFoundException;
import br.com.central.erros.impl.business.repository.V1.LogRepositoryV1;
import org.assertj.core.data.Index;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LogServiceImplV1Test {

    private ArrayList<LogV1> logList;

    @Mock
    private LogRepositoryV1 logRepositoryV1;

    @InjectMocks
    private LogServiceImplV1 service;

    @Before
    public void setUpLogList() {
        logList = new ArrayList<>();
        final UserV1 collector = new UserV1(1,"João", "joao@123.com",
                "123", UserType.PESSOAFISICA, "$2$546");
        final LogV1 log = new LogV1(0, "127.0.0.1", 1L, LocalDate.now(), "Log",
                "Erro de Java",  Environment.DEVELOPMENT, Level.DEBUG, collector, true);
        final LogV1 log2 = new LogV1(0, "localhost", 1L, LocalDate.now(), "Log2",
                "Erro de Spring",  Environment.DEVELOPMENT, Level.ERROR, collector, true);
        logList.add(log);
        logList.add(log2);
    }

    @Test
    public void saves() {
        final LogDTOV1 actualDto = LogConverter.logToDTO(logList.get(0));

        final LogDTOV1 expected = new LogDTOV1(actualDto.getIp(), actualDto.getNumberOfEvents(),
                actualDto.getDate(), actualDto.getTitle(), actualDto.getDetails(), actualDto.getActive(), actualDto.getEnvironment(),
                actualDto.getLevel(), actualDto.getUser());

        when(logRepositoryV1.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        LogDTOV1 actual = service.save(actualDto);

        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    @Test
    public void findsLogById() {
        final LogDTOV1 expected = LogConverter.logToDTO(logList.get(0));

        when(logRepositoryV1.findById(1)).thenReturn(Optional.of(LogConverter.logDTOToEntity(expected)));
        final LogDTOV1 actual = service.findById(1);
        assertThat(actual).isEqualToComparingFieldByFieldRecursively(expected);
    }

    @Test(expected = ObjectNotFoundException.class)
    public void throwsExceptionWhenLogNotFound() {
        when(logRepositoryV1.findById(1)).thenReturn(Optional.empty());
        service.findById(1);
    }

    @Test
    public void updatesLog() {
        final LogDTOV1 expected = LogConverter.logToDTO(logList.get(0));

        when(logRepositoryV1.save(any())).thenAnswer(invocation -> invocation.getArgument(0));
        ArgumentCaptor<LogV1> captor = ArgumentCaptor.forClass(LogV1.class);
        LogDTOV1 actual = service.update(1, expected);
        assertThat(actual).isEqualToComparingFieldByField(expected);
        verify(logRepositoryV1, times(1)).save(captor.capture());
        Assert.assertTrue(1 == captor.getValue().getId());

    }

    @Test
    public void findsUserLogs() {
        when(logRepositoryV1.findAllByUser_IdAndEnvironmentAndActiveTrue(1, Environment.DEVELOPMENT)).thenReturn(logList);
        List<LogDTOV1> actual = service.findAllByUser(1, Environment.DEVELOPMENT, Optional.empty(), Optional.empty(), Optional.empty());
        assertThat(actual).extracting("title", String.class).contains("Log", Index.atIndex(0));
    }

    @Test
    public void findsAllUserLogsByLevel() {
        when(logRepositoryV1.findAllByUser_IdAndEnvironmentAndActiveTrue(1, Environment.DEVELOPMENT)).thenReturn(logList);

        List<LogDTOV1> actual = service.findAllByUser(1, Environment.DEVELOPMENT, Optional.empty(), Optional.of(FindBy.LEVEL),
                Optional.of(Level.ERROR.toString()));

        assertThat(actual).extracting("title", String.class).containsOnly("Log2");
    }

    @Test
    public void findsAllUserLogsByDescription() {
        when(logRepositoryV1.findAllByUser_IdAndEnvironmentAndActiveTrue(1, Environment.DEVELOPMENT)).thenReturn(logList);

        List<LogDTOV1> actual = service.findAllByUser(1, Environment.DEVELOPMENT, Optional.empty(), Optional.of(FindBy.DESCRIPTION),
                Optional.of("java"));

        assertThat(actual).extracting("title", String.class).containsOnly("Log");
    }

    @Test
    public void findsAllUserLogsByOrigin() {
        when(logRepositoryV1.findAllByUser_IdAndEnvironmentAndActiveTrue(1, Environment.DEVELOPMENT)).thenReturn(logList);

        List<LogDTOV1> actual = service.findAllByUser(1, Environment.DEVELOPMENT, Optional.empty(), Optional.of(FindBy.ORIGIN),
                Optional.of("127.0.0.1"));

        assertThat(actual).extracting("title", String.class).containsOnly("Log");
    }
}
