package br.com.central.erros.impl.business.service.V1.contracts;

import br.com.central.erros.impl.business.dto.VerificationCodeDTO;
import br.com.central.erros.impl.business.entity.V1.VerificationCode;

public interface VerificationCodeService {
    VerificationCode create(String userEmail);
    void delete(String email);
    boolean isValid(VerificationCodeDTO codeDTO);
}
