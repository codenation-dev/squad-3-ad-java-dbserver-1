package br.com.central.erros.impl.business.service.V1;


import br.com.central.erros.impl.business.dto.LogDTOV1;
import br.com.central.erros.impl.business.entity.V1.LogV1;
import br.com.central.erros.impl.business.entity.converter.LogConverter;
import br.com.central.erros.impl.business.repository.V1.LogRepositoryV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LogServiceImplV1 implements LogServiceV1 {

    private LogRepositoryV1 logRepositoryV1;

    @Autowired
    public LogServiceImplV1(LogRepositoryV1 logRepositoryV1) {

        this.logRepositoryV1 = logRepositoryV1;
    }

    @Override
    public Optional<LogDTOV1> buscaLogById(Long logId) {
        Optional<LogV1> optionalLogV1 = logRepositoryV1.findById(logId);

        Optional<LogDTOV1> logDTOV1 = optionalLogV1.map(LogConverter::toDTOV1);
        return logDTOV1;
    }
}




