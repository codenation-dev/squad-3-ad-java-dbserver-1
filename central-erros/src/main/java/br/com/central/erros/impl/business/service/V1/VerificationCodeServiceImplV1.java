package br.com.central.erros.impl.business.service.V1;

import br.com.central.erros.impl.business.dto.VerificationCodeDTO;
import br.com.central.erros.impl.business.entity.V1.UserV1;
import br.com.central.erros.impl.business.entity.V1.VerificationCode;
import br.com.central.erros.impl.business.repository.V1.VerificationCodeRepository;
import br.com.central.erros.impl.business.service.V1.contracts.VerificationCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class VerificationCodeServiceImplV1 implements VerificationCodeService {

    private VerificationCodeRepository repository;

    @Autowired
    public VerificationCodeServiceImplV1(VerificationCodeRepository repository) {
        this.repository = repository;
    }

    @Override
    public VerificationCode create(String userEmail) {
        VerificationCode code = new VerificationCode(generateNumericCode(), userEmail);
        return repository.save(code);
    }

    @Override
    public void delete(String email) {
        repository.deleteByEmail(email);
    }

    @Override
    public boolean isValid(VerificationCodeDTO code) {
        Optional<VerificationCode> actual = repository.findByToken(code.getToken());
        if(actual.isPresent()) {
            return actual.get().getEmail().equals(code.getEmail());
        }
        return false;
    }

    private String generateNumericCode() {
        return String.format("%04d", new Random().nextInt(10000));
    }
}
