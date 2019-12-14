package br.com.central.erros.impl.api.V1.contracts;

import br.com.central.erros.impl.business.dto.VerificationCodeDTO;
import org.springframework.http.ResponseEntity;

public interface AuthRestEndpointV1 {
    ResponseEntity<Void> sendRecoveryCode(String email);
    ResponseEntity<Void> changeUserPassword(VerificationCodeDTO code, String password);
}
