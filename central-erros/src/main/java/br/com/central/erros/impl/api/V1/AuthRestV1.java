package br.com.central.erros.impl.api.V1;

import br.com.central.erros.impl.business.dto.VerificationCodeDTO;
import br.com.central.erros.impl.business.service.V1.AuthServiceImplV1;
import br.com.central.erros.impl.business.service.V1.EmailServiceImplV1;
import br.com.central.erros.impl.business.service.V1.contracts.EmailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/recovery")
@Api(value = "Auth",  tags = { "Auth" })
public class AuthRestV1 {

    private final AuthServiceImplV1 authService;
    private final EmailService emailService;

    @Bean
    public EmailService emailService() {
        return new EmailServiceImplV1();
    }

    @Autowired
    public AuthRestV1(AuthServiceImplV1 authService, EmailService emailService) {
        this.authService = authService;
        this.emailService = emailService;
    }

    @ApiOperation(value = "Envia um código de verificação para o e-mail especificado")
    @GetMapping(value = "/{email:.+}")
    public ResponseEntity<Void> sendRecoveryCode(@PathVariable(value = "email") String email) {
        emailService.sendVerificationCode(email);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Troca a senha do usuário")
    @PostMapping(value = "/{password:.+}")
    public ResponseEntity<Void> changeUserPassword(@RequestBody VerificationCodeDTO code,
                                                   @PathVariable(value = "password") String password) {
        authService.updateUserPassword(code, password);
        return ResponseEntity.noContent().build();
    }
}
