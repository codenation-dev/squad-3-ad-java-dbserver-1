package br.com.central.erros.impl.business.entity.converter;

import java.util.Objects;

import br.com.central.erros.impl.business.dto.LogDTOV1;
import br.com.central.erros.impl.business.entity.V1.LogV1;
import br.com.central.erros.impl.business.exception.UserExceptionMessage;
import br.com.central.erros.impl.business.exception.exceptions.ValidationException;


public class LogConverter {


    public static LogV1 logDTOToEntity(LogDTOV1  logDTOV1) {

        if (Objects.isNull(logDTOV1)) {
            throw new ValidationException(UserExceptionMessage.ERROR_DADOS_INVALIDOS_LOG);
        }

        LogV1 logV1 = new LogV1(null, logDTOV1.getIp(), logDTOV1.getNumeroDeEventos(), logDTOV1.getDataDoErro(),
                logDTOV1.getTitulo(), logDTOV1.getDetalhes(), logDTOV1.getAmbiente(), logDTOV1.getLevel(),
                logDTOV1.getUserV1());

        if (Objects.nonNull(logDTOV1.getUserV1())){
            logV1.setUserV1(logV1.getUserV1());
        }
        return logV1;
    }

    public static LogDTOV1 logToDTO(LogV1 logV1) {

        if (Objects.isNull(logV1)) {
            throw new ValidationException(UserExceptionMessage.ERROR_DADOS_INVALIDOS_LOG);
        }

        LogDTOV1 logDTOV1 = new LogDTOV1(logV1.getIp(), logV1.getNumeroDeEventos(), logV1.getDataDoErro(),
                logV1.getTitulo(), logV1.getDetalhes(), logV1.getAmbiente(), logV1.getLevel(), logV1.getUserV1());

        if (Objects.nonNull(logV1.getUserV1())){
            logDTOV1.setUserV1(logV1.getUserV1());
        }

      return logDTOV1;
    }

}
