package br.com.central.erros.impl.business.repository.V5;

import br.com.central.erros.impl.business.entity.V1.LogV1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepositoryV1 extends JpaRepository<LogV1, Long> {

}
