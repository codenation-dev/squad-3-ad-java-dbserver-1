package br.com.central.erros.impl.business.service.V1;

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

    public VerificationCode create(UserV1 owner) {
        VerificationCode code = new VerificationCode(generateNumericCode(), owner);
        return repository.save(code);
    }

    public boolean isValid(VerificationCode code) {
        Optional<VerificationCode> actual = repository.findById(code.getId());
        if(actual.isPresent()) {
            return actual.get().equals(code);
        }
        return false;
    }

    public void delete(VerificationCode code) {
        repository.delete(code);
    }

    public boolean exists(VerificationCode code) {
        return repository.existsById(code.getId());
    }

    private String generateNumericCode() {
        return String.format("%04d", new Random().nextInt(10000));
    }
}
