package br.com.central.erros.impl.business.repository.V1;


import java.util.List;

import br.com.central.erros.impl.business.entity.V1.LogV1;
import br.com.central.erros.impl.business.entity.enums.Ambiente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepositoryV1 extends JpaRepository<LogV1, Integer> {

    List<LogV1> findByUserV1Id(Integer id);
   // List<LogV1> findByLevel(Level descricao);
    List<LogV1> findByAmbiente(Ambiente ambiente);
   // List<LogV1> findByDetalhesContaining(String detalhes);


}
