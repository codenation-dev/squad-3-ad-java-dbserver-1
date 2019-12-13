package br.com.central.erros.impl.business.repository.V1;

import br.com.central.erros.impl.business.entity.V1.VerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VerificationCodeRepository extends JpaRepository<VerificationCode, Long> {
    Optional<VerificationCode> findByToken(String email);
    void deleteByEmail(String email);
}
