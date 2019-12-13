package br.com.central.erros.impl.business.service.V1;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.com.central.erros.impl.business.dto.LogDTOV1;
import br.com.central.erros.impl.business.entity.V1.LogV1;
import br.com.central.erros.impl.business.entity.converter.LogConverter;
import br.com.central.erros.impl.business.exception.LogExceptionMessage;
import br.com.central.erros.impl.business.exception.exceptions.ObjectNotFoundException;
import br.com.central.erros.impl.business.repository.V1.LogRepositoryV1;
import br.com.central.erros.impl.business.service.V1.contracts.LogServiceV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImplV1 implements LogServiceV1 {

    private LogRepositoryV1 logRepositoryV1;

    @Autowired
    public LogServiceImplV1(LogRepositoryV1 logRepositoryV1) {
        this.logRepositoryV1 = logRepositoryV1;
    }

    @Override
    public List<LogDTOV1> buscarTodos() {
        List<LogV1> logEntityList = logRepositoryV1.findAll();
        List<LogDTOV1> logDtoList = logEntityList.stream().map(LogConverter::logToDTO).collect(Collectors.toList());
        return logDtoList;
    }

    @Override
    public LogDTOV1 salvar(LogDTOV1 logInput) {
        LogV1 logEntity = LogConverter.logDTOToEntity(logInput);
        LogV1 logSalvoNoBanco = logRepositoryV1.save(logEntity);
        return LogConverter.logToDTO(logSalvoNoBanco);
    }

    @Override
    public List<LogDTOV1> buscarPorUsuario(Integer idUsuario) {
        return logRepositoryV1.findAllByUserV1_Id(idUsuario).stream()
                .map(LogConverter::logToDTO).collect(Collectors.toList());
    }

    @Override
    public LogDTOV1 buscarPorId(Integer id) {
        LogV1 log = logRepositoryV1.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(LogExceptionMessage.INEXISTENTE));
        return LogConverter.logToDTO(log);
    }

}