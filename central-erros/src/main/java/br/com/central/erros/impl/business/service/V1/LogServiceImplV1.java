package br.com.central.erros.impl.business.service.V1;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.com.central.erros.impl.business.dto.LogDTOV1;
import br.com.central.erros.impl.business.entity.V1.LogV1;
import br.com.central.erros.impl.business.entity.converter.LogConverter;
import br.com.central.erros.impl.business.entity.enums.Ambiente;
import br.com.central.erros.impl.business.entity.enums.BuscaPor;
import br.com.central.erros.impl.business.entity.enums.Level;
import br.com.central.erros.impl.business.entity.enums.OrdenarPor;
import br.com.central.erros.impl.business.exception.exceptions.ObjectNotFoundException;
import br.com.central.erros.impl.business.repository.V1.LogRepositoryV1;
import br.com.central.erros.impl.business.service.V1.contracts.LogServiceV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImplV1 implements LogServiceV1 {

    private LogRepositoryV1 logRepositoryV1;
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Autowired
    public LogServiceImplV1(LogRepositoryV1 logRepositoryV1) {
        this.logRepositoryV1 = logRepositoryV1;
    }

    @Override
    public List<LogDTOV1> buscarTodosLogs() {

        List<LogV1> logEntity = logRepositoryV1.findAll();
        List<LogDTOV1> listaLogDTOV1 = logEntity.stream().map(LogConverter::logToDTO).collect(Collectors.toList());

        return listaLogDTOV1;
    }



    @Override
    public List<LogDTOV1> buscarTodosLogsDoUsuario(Ambiente ambiente, Optional<OrdenarPor> ordenarPor, Optional<BuscaPor> buscarPor, Optional<String> descricaoBusca) {
        List<LogV1> logEntity = logRepositoryV1.findByAmbiente(ambiente);

            if(buscarPor.isPresent() && descricaoBusca.isPresent()){
                logEntity =  buscarPor.get().metodoBuscarPor(logEntity, descricaoBusca.get());
            }

            if(ordenarPor.isPresent()){
                logEntity =  ordenarPor.get().metodoOrdenarPor(logEntity);
            }




        List<LogDTOV1> listaLogDTOV1 = logEntity.stream().map(LogConverter::logToDTO).collect(Collectors.toList());

        return listaLogDTOV1;
    }


    @Override
    public LogDTOV1 salvarNovoLog(LogDTOV1 logInput) {

        LogV1 logEntity = LogConverter.logDTOToEntity(logInput);

        LogV1 logSalvoNoBanco = logRepositoryV1.save(logEntity);

        return LogConverter.logToDTO(logSalvoNoBanco);
    }

/*    @Override
    public LogDTOV1 encontrarLogPeloId(Integer id) {
        Optional<LogV1> byId = logRepositoryV1.findById(id);

        Optional<LogDTOV1> logDTOV1 = byId.map(LogConverter::logToDTO);

        logDTOV1.orElseThrow(() -> new ObjectNotFoundException(
                "LogV1 não encontrado! Id: " + id + ", Tipo: " + LogV1.class.getName()));

        return logDTOV1.get();
    }*/

    @Override
    public List<LogDTOV1> buscarLogsPorUsuario(Integer idUsuario) {
        List<LogV1> all = logRepositoryV1.findAll();

        List<LogDTOV1> collect1 =
                all.stream().filter(logV1 -> logV1.getUserV1().getId().equals(idUsuario)).map(LogConverter::logToDTO).collect(Collectors.toList());

        return collect1;
    }

    @Override
    public Optional<LogDTOV1> encontrarLogPeloId(Integer id) {

        Optional<LogV1> optionalUserV1 = logRepositoryV1.findById(id);
        return optionalUserV1.map(LogConverter::logToDTO);
    }

}