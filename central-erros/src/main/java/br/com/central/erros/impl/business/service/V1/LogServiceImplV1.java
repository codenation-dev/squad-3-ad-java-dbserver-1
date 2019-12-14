package br.com.central.erros.impl.business.service.V1;


import br.com.central.erros.impl.business.dto.LogDTOV1;
import br.com.central.erros.impl.business.entity.V1.LogV1;
import br.com.central.erros.impl.business.entity.converter.LogConverter;
import br.com.central.erros.impl.business.exception.LogExceptionMessage;
import br.com.central.erros.impl.business.exception.exceptions.ObjectNotFoundException;
import br.com.central.erros.impl.business.repository.V1.LogRepositoryV1;
import br.com.central.erros.impl.business.service.V1.contracts.LogServiceV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LogServiceImplV1 implements LogServiceV1 {

    private final LogRepositoryV1 logRepositoryV1;

    @Autowired
    public LogServiceImplV1(LogRepositoryV1 logRepositoryV1) {
        this.logRepositoryV1 = logRepositoryV1;
    }

    @Override
    public List<LogDTOV1> findAll() {
        List<LogV1> logEntityList = logRepositoryV1.findAll();
        List<LogDTOV1> logDtoList = logEntityList.stream().map(LogConverter::logToDTO).collect(Collectors.toList());
        return logDtoList;
    }

    @Override
    public LogDTOV1 save(LogDTOV1 logInput) {
        LogV1 logEntity = LogConverter.logDTOToEntity(logInput);
        LogV1 logInDatabase = logRepositoryV1.save(logEntity);
        return LogConverter.logToDTO(logInDatabase);
    }

    @Override
    public List<LogDTOV1> findByUser(Integer userId) {
        return logRepositoryV1.findAllByUser_Id(userId).stream()
                .map(LogConverter::logToDTO).collect(Collectors.toList());
    }

    @Override
    public LogDTOV1 findById(Integer id) {
        LogV1 log = logRepositoryV1.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(LogExceptionMessage.NOT_FOUND));
        return LogConverter.logToDTO(log);
    }

}