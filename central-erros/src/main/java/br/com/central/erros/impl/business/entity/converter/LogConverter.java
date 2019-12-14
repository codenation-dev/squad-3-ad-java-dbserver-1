package br.com.central.erros.impl.business.entity.converter;

import br.com.central.erros.impl.business.dto.LogDTOV1;
import br.com.central.erros.impl.business.entity.V1.LogV1;
import br.com.central.erros.impl.business.exception.LogExceptionMessage;

public class LogConverter extends Converter {

    public static LogV1 logDTOToEntity(LogDTOV1 logDTOV1) {
        throwExceptionIfNull(logDTOV1, LogExceptionMessage.INVALID_DATA);
        return new LogV1(null, logDTOV1.getIp(), logDTOV1.getNumberOfEvents(), logDTOV1.getDate(),
                logDTOV1.getTitle(), logDTOV1.getDetails(), logDTOV1.getEnvironment(),  logDTOV1.getLevel(),
                logDTOV1.getUser());
    }

    public static LogDTOV1 logToDTO(LogV1 logV1) {
        throwExceptionIfNull(logV1, LogExceptionMessage.INVALID_DATA);
        return new LogDTOV1(logV1.getIp(), logV1.getNumberOfEvents(), logV1.getDate(),
                logV1.getTitle(), logV1.getDetails(), logV1.getEnvironment(), logV1.getLevel(), logV1.getUser());
    }

}
