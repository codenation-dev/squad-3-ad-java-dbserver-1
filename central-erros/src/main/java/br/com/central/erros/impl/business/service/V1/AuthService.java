package br.com.central.erros.impl.business.service.V1;

import br.com.central.erros.impl.business.entity.V1.UserV1;
import br.com.central.erros.impl.business.entity.V1.VerificationCode;
import br.com.central.erros.impl.business.exception.exceptions.ValidationException;
import br.com.central.erros.impl.business.repository.V1.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final VerificationCodeService verificationCodeService;

    @Autowired
    public AuthService(UserRepository userRepository, VerificationCodeService verificationCodeService) {
        this.userRepository = userRepository;
        this.verificationCodeService = verificationCodeService;
    }

    public void updateUserPassword(VerificationCode code, String newPassword) {
        if(verificationCodeService.isValid(code)) {
            UserV1 user = code.getUser();
            user.setSenha(newPassword);
            userRepository.save(user);
        } else {
            throw new ValidationException("Código de usuário inválido");
        }
    }
}
