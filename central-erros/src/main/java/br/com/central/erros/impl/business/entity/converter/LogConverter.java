package br.com.central.erros.impl.business.entity.converter;


import br.com.central.erros.impl.business.dto.LogDTOV1;
import br.com.central.erros.impl.business.entity.V1.LogV1;
import br.com.central.erros.impl.business.exception.LogExceptionMessage;


public class LogConverter extends Converter {

    public static LogV1 logDTOToEntity(LogDTOV1 logDTOV1) {
        jogarExcessaoSeObjetoNulo(logDTOV1, LogExceptionMessage.ERRO_DADOS_DA_LOG_INVALIDOS);
        return new LogV1(null, logDTOV1.getIp(), logDTOV1.getNumeroDeEventos(), logDTOV1.getDataDoErro(),
                logDTOV1.getTitulo(), logDTOV1.getDetalhes(), logDTOV1.getAmbiente(),  logDTOV1.getLevel(),
                logDTOV1.getUserV1());
    }

    public static LogDTOV1 logToDTO(LogV1 logV1) {
        jogarExcessaoSeObjetoNulo(logV1, LogExceptionMessage.ERRO_DADOS_DA_LOG_INVALIDOS);
        return new LogDTOV1(logV1.getIp(), logV1.getNumeroDeEventos(), logV1.getDataDoErro(),
                logV1.getTitulo(), logV1.getDetalhes(), logV1.getAmbiente(), logV1.getLevel(), logV1.getUserV1());
    }

}
