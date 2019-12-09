package br.com.central.erros.impl.business.service.V1;

import br.com.central.erros.impl.business.entity.V1.VerificationCode;
import br.com.central.erros.impl.business.repository.V1.VerificationCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerificationCodeService {

    private VerificationCodeRepository repository;

    @Autowired
    public VerificationCodeService(VerificationCodeRepository repository) {
        this.repository = repository;
    }

    public VerificationCode save(VerificationCode code) {
        return repository.save(code);
    }

    public void delete(VerificationCode code) {
        repository.delete(code);
    }
}
