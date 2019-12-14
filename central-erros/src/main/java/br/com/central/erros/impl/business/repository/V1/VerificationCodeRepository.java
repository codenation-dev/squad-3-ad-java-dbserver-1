package br.com.central.erros.impl.business.repository.V1;

import br.com.central.erros.impl.business.entity.V1.VerificationCodeV1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VerificationCodeRepository extends JpaRepository<VerificationCodeV1, Long> {
    Optional<VerificationCodeV1> findByToken(String email);
    void deleteByEmail(String email);
}
