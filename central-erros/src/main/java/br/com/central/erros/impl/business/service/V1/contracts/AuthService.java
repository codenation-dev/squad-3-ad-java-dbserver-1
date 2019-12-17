package br.com.central.erros.impl.business.service.V1.contracts;

import br.com.central.erros.impl.business.dto.VerificationCodeDTO;

public interface AuthService {
    void updateUserPassword(VerificationCodeDTO code, String newPassword);
}
