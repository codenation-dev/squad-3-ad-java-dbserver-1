package br.com.central.erros.impl.api.V1;

import br.com.central.erros.impl.business.dto.VerificationCodeDTO;
import br.com.central.erros.impl.business.entity.V1.UserV1;
import br.com.central.erros.impl.business.exception.exceptions.ObjectNotFoundException;
import br.com.central.erros.impl.business.exception.exceptions.ValidationException;
import br.com.central.erros.impl.business.service.V1.AuthService;
import br.com.central.erros.impl.business.service.V1.EmailServiceImpl;
import br.com.central.erros.impl.business.service.V1.contracts.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/v1/recovery")
public class AuthRestV1 {

    private final AuthService authService;
    private final EmailService emailService;

    @Bean
    public EmailService emailService() {
        return new EmailServiceImpl();
    }

    @Autowired
    public AuthRestV1(AuthService authService, EmailService emailService) {
        this.authService = authService;
        this.emailService = emailService;
    }

    @PostMapping(value = "/recoveryCode")
    public ResponseEntity<Void> recovery(@Valid @RequestBody UserV1 user) {
        emailService.sendVerificationCode(user);
        return ResponseEntity.noContent().build();
    }
    @PostMapping(value = "/changePassword/{password}")
    public ResponseEntity<Void> changeUserPassword(@RequestBody VerificationCodeDTO code, @PathVariable String password) {
        authService.updateUserPassword(code, password);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler({ObjectNotFoundException.class})
    public ResponseEntity handleNotFoundException() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler({ValidationException.class})
    public ResponseEntity handleValidationException() {
        return ResponseEntity.unprocessableEntity().build();
    }

}
