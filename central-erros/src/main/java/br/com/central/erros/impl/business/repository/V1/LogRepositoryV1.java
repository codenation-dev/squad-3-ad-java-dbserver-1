package br.com.central.erros.impl.business.repository.V1;


import java.util.List;

import br.com.central.erros.impl.business.entity.V1.LogV1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepositoryV1 extends JpaRepository<LogV1, Integer> {
    List<LogV1> findAllByUserV1_Id(Integer id);
}
