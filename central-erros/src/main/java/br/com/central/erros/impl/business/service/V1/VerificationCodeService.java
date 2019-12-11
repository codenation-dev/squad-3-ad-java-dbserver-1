package br.com.central.erros.impl.business.service.V1;

import br.com.central.erros.impl.business.dto.VerificationCodeDTO;
import br.com.central.erros.impl.business.entity.V1.UserV1;
import br.com.central.erros.impl.business.entity.V1.VerificationCode;
import br.com.central.erros.impl.business.repository.V1.VerificationCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class VerificationCodeService {

    private VerificationCodeRepository repository;

    @Autowired
    public VerificationCodeService(VerificationCodeRepository repository) {
        this.repository = repository;
    }

    public VerificationCode create(String userEmail) {
        VerificationCode code = new VerificationCode(generateNumericCode(), userEmail);
        return repository.save(code);
    }

    public boolean isValid(VerificationCodeDTO code) {
        Optional<VerificationCode> actual = repository.findByToken(code.getToken());
        if(actual.isPresent()) {
            return actual.get().getEmail().equals(code.getEmail());
        }
        return false;
    }

    public void deleteByEmail(String email) {
        repository.deleteByEmail(email);
    }

    public boolean exists(VerificationCode code) {
        return repository.existsById(code.getId());
    }

    private String generateNumericCode() {
        return String.format("%04d", new Random().nextInt(10000));
    }
}
