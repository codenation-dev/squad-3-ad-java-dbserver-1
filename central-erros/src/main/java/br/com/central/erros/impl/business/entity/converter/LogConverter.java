package br.com.central.erros.impl.business.entity.converter;

import java.util.Objects;

import br.com.central.erros.impl.business.dto.LogDTOV1;
import br.com.central.erros.impl.business.entity.V1.LogV1;
import br.com.central.erros.impl.business.exception.LogExceptionMessage;
import br.com.central.erros.impl.business.exception.exceptions.ValidationException;


public class LogConverter {

    private static void jogarExcessaoSeLogNula(Object log) {
        if (Objects.isNull(log)) {
            throw new ValidationException(LogExceptionMessage.ERRO_DADOS_DA_LOG_INVALIDOS);
        }
    }

    public static LogV1 logDTOToEntity(LogDTOV1 logDTOV1) {
        jogarExcessaoSeLogNula(logDTOV1);
        return new LogV1(null, logDTOV1.getIp(), logDTOV1.getNumeroDeEventos(), logDTOV1.getDataDoErro(),
                logDTOV1.getTitulo(), logDTOV1.getDetalhes(), logDTOV1.getAmbiente(),  logDTOV1.getLevel(),
                logDTOV1.getUserV1());
    }

    public static LogDTOV1 logToDTO(LogV1 logV1) {
        jogarExcessaoSeLogNula(logV1);
        return new LogDTOV1(logV1.getIp(), logV1.getNumeroDeEventos(), logV1.getDataDoErro(),
                logV1.getTitulo(), logV1.getDetalhes(), logV1.getAmbiente(), logV1.getLevel(), logV1.getUserV1());
    }

}
