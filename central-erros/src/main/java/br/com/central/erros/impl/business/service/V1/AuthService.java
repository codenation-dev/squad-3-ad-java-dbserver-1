package br.com.central.erros.impl.business.service.V1;

import br.com.central.erros.impl.business.dto.UserDTOV1;
import br.com.central.erros.impl.business.dto.VerificationCodeDTO;
import br.com.central.erros.impl.business.entity.V1.UserV1;
import br.com.central.erros.impl.business.entity.converter.UserConverter;
import br.com.central.erros.impl.business.exception.exceptions.ValidationException;
import br.com.central.erros.impl.business.repository.V1.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserServiceImplV1 userService;
    private final VerificationCodeService verificationCodeService;
    private final UserRepository userRepository;

    @Autowired
    public AuthService(UserServiceImplV1 userService, VerificationCodeService verificationCodeService, UserRepository userRepository) {
        this.userService = userService;
        this.verificationCodeService = verificationCodeService;
        this.userRepository = userRepository;
    }

    /*
    Aqui há o problema com o usuário do Spring Security
    não é possível dar find pelo service porque pode dar erro
     */
    public void updateUserPassword(VerificationCodeDTO code, String newPassword) {
        if(verificationCodeService.isValid(code)) {
            UserV1 user = userRepository.findById(code.getUserId()).get();
            user.setSenha(newPassword);
            userService.salvarNovoUSuario(UserConverter.userToDTO(user));
        } else {
            throw new ValidationException("Código de usuário inválido");
        }
    }
}
