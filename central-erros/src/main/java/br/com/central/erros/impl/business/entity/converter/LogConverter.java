package br.com.central.erros.impl.business.entity.converter;

import br.com.central.erros.impl.business.dto.LogDTOV1;
import br.com.central.erros.impl.business.entity.V1.LogV1;
import br.com.central.erros.impl.business.exception.UserExceptionMessage;
import br.com.central.erros.impl.business.exception.exceptions.ValidationException;

import java.util.Objects;

public class LogConverter {
    public static LogV1 toEntity(LogDTOV1 logDTOV1) {
        if (Objects.isNull(logDTOV1)) {
            throw new ValidationException(UserExceptionMessage.ERROR_DADOS_INVALIDOS_USER);
        }
        return new LogV1.Builder()
                .id(logDTOV1.getId())
                .ambiente(logDTOV1.getAmbiente())
                .level(logDTOV1.getLevel())
                .ip(logDTOV1.getIp())
                .numeroDeEventos(logDTOV1.getNumeroDeEventos())
                .titulo(logDTOV1.getTitulo())
                .coletor(logDTOV1.getColetor())
                .dataDoErro(logDTOV1.getDataDoErro())
                .detalhes(logDTOV1.getDetalhes())
                .build();
    }

    public static LogDTOV1 toDTOV1(LogV1 logV1){
        if(Objects.isNull(logV1)){
            return null;
        }
        return new LogDTOV1.Builder()
                .id(logV1.getId())
                .ambiente(logV1.getAmbiente())
                .level(logV1.getLevel())
                .ip(logV1.getIp())
                .numeroDeEventos(logV1.getNumeroDeEventos())
                .titulo(logV1.getTitulo())
                .coletor(logV1.getColetor())
                .dataDoErro(logV1.getDataDoErro())
                .detalhes(logV1.getDetalhes())
                .build();
    }
}
