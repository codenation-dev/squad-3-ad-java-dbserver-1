package br.com.central.erros.impl.business.service.V1;


import br.com.central.erros.impl.business.dto.LogDTOV1;
import br.com.central.erros.impl.business.entity.V1.LogV1;
import br.com.central.erros.impl.business.entity.converter.LogConverter;
import br.com.central.erros.impl.business.entity.enums.Environment;
import br.com.central.erros.impl.business.entity.enums.FindBy;
import br.com.central.erros.impl.business.entity.enums.OrderBy;
import br.com.central.erros.impl.business.exception.LogExceptionMessage;
import br.com.central.erros.impl.business.exception.exceptions.ObjectNotFoundException;
import br.com.central.erros.impl.business.repository.V1.LogRepositoryV1;
import br.com.central.erros.impl.business.service.V1.contracts.LogServiceV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LogServiceImplV1 implements LogServiceV1 {

    private final LogRepositoryV1 logRepositoryV1;

    @Autowired
    public LogServiceImplV1(LogRepositoryV1 logRepositoryV1) {
        this.logRepositoryV1 = logRepositoryV1;
    }



    @Override
    public LogDTOV1 save(LogDTOV1 logInput) {
        LogV1 logEntity = LogConverter.logDTOToEntity(logInput);
        LogV1 logInDatabase = logRepositoryV1.save(logEntity);
        return LogConverter.logToDTO(logInDatabase);
    }



    @Override
    public LogDTOV1 findById(Integer id) {
        LogV1 log = logRepositoryV1.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(LogExceptionMessage.NOT_FOUND));
        return LogConverter.logToDTO(log);
    }

    @Override
    public List<LogDTOV1> findAllByUser(Environment environment, Optional<OrderBy> orderBy, Optional<FindBy> findBy, Optional<String> stringFilter) {
        List<LogV1> logEntity = logRepositoryV1.findByEnvironment(environment);

        if(findBy.isPresent() && stringFilter.isPresent()){
            logEntity =  findBy.get().methodFindBy(logEntity, stringFilter.get());
        }

        if(orderBy.isPresent()){
            logEntity =  orderBy.get().methodOrderBy(logEntity);
        }

        List<LogDTOV1> listaLogDTOV1 = logEntity.stream().map(LogConverter::logToDTO).collect(Collectors.toList());

        return listaLogDTOV1;
    }

}