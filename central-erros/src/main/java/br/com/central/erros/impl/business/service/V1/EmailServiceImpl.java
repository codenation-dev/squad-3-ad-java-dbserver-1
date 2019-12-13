package br.com.central.erros.impl.business.service.V1;

import br.com.central.erros.impl.business.dto.UserDTOV1;
import br.com.central.erros.impl.business.entity.V1.UserV1;
import br.com.central.erros.impl.business.entity.V1.VerificationCode;
import br.com.central.erros.impl.business.entity.converter.UserConverter;
import br.com.central.erros.impl.business.exception.exceptions.ObjectNotFoundException;
import br.com.central.erros.impl.business.service.V1.contracts.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class EmailServiceImpl implements EmailService {

    private static final String EMAIL_INVALIDO = "O e-mail inserido não pertence a nenhum usuário cadastrado!";

    @Value("${default.sender}")
    private String sender;

    @Autowired
    private VerificationCodeService verificationCodeService;

    @Autowired
    private UserServiceImplV1 userService;

    @Autowired
    private MailSender mailSender;

    // $2a$10$xIQwRdncBMVMNOY8E6M0auS8UB1VqCXc2o482wGtzoH1ir3Z.huAS
    // $2a$10$xIQwRdncBMVMNOY8E6M0auS8UB1VqCXc2o482wGtzoH1ir3Z.huAS

    public void sendVerificationCode(String email) {
        if(userService.existeUsuarioComEmail(email)) {
            VerificationCode code = verificationCodeService.create(email);
            sendMessage(prepareMessage(email, code.getToken()));
        } else {
            throw new ObjectNotFoundException(EMAIL_INVALIDO);
        }
    }

    private void sendMessage(SimpleMailMessage sm) {
        mailSender.send(sm);
    }

    private SimpleMailMessage prepareMessage(String email, String verificationCode) {
        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setTo(email);
        sm.setFrom(sender);
        sm.setSubject("Recuperação de senha - Squid Central de Erros");
        sm.setSentDate(new Date(System.currentTimeMillis()));
        sm.setText("Seu código para recuperação de senha é: " + verificationCode);
        return sm;
    }
}
