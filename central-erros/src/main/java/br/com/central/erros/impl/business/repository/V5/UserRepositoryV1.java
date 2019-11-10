package br.com.central.erros.impl.business.repository.V5;

import br.com.central.erros.impl.business.entity.V1.UserV1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepositoryV1 extends JpaRepository<UserV1, Long> {


    @Transactional(readOnly = true)
    UserV1 findByEmail(String email);

}
