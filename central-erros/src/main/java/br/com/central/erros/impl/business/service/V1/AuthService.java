package br.com.central.erros.impl.business.service.V1;

import br.com.central.erros.impl.business.dto.UserDTOV1;
import br.com.central.erros.impl.business.dto.VerificationCodeDTO;
import br.com.central.erros.impl.business.entity.V1.UserV1;
import br.com.central.erros.impl.business.entity.converter.UserConverter;
import br.com.central.erros.impl.business.exception.exceptions.ValidationException;
import br.com.central.erros.impl.business.repository.V1.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class AuthService {

    private final UserServiceImplV1 userService;
    private final VerificationCodeService verificationCodeService;

    @Autowired
    public AuthService(UserServiceImplV1 userService, VerificationCodeService verificationCodeService, UserRepository userRepository) {
        this.userService = userService;
        this.verificationCodeService = verificationCodeService;
    }

    public void updateUserPassword(VerificationCodeDTO code, String newPassword) {
        if(verificationCodeService.isValid(code)) {
            UserDTOV1 user = userService.findByEmail(code.getEmail());
            user.setSenha(newPassword);
            userService.salvarNovoUSuario(user);
            verificationCodeService.deleteByEmail(code.getEmail());
        } else {
            throw new ValidationException("Código de usuário inválido");
        }
    }
}
