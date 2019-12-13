package br.com.central.erros.impl.business.service.V1;

import br.com.central.erros.impl.business.dto.UserDTOV1;
import br.com.central.erros.impl.business.dto.VerificationCodeDTO;
import br.com.central.erros.impl.business.exception.exceptions.ValidationException;
import br.com.central.erros.impl.business.service.V1.contracts.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class AuthServiceImplV1 implements AuthService {

    private final UserServiceImplV1 userService;
    private final VerificationCodeServiceImplV1 verificationCodeService;

    @Autowired
    public AuthServiceImplV1(UserServiceImplV1 userService, VerificationCodeServiceImplV1 verificationCodeService) {
        this.userService = userService;
        this.verificationCodeService = verificationCodeService;
    }

    @Override
    public void updateUserPassword(VerificationCodeDTO code, String newPassword) {
        if(verificationCodeService.isValid(code)) {
            UserDTOV1 user = userService.findByEmail(code.getEmail());
            user.setSenha(newPassword);
            userService.salvarNovoUSuario(user);
            verificationCodeService.delete(code.getEmail());
        } else {
            throw new ValidationException("Código de usuário inválido");
        }
    }
}
