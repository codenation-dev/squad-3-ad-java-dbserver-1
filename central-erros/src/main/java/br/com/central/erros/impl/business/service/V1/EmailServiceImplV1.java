package br.com.central.erros.impl.business.service.V1;

import br.com.central.erros.impl.business.entity.V1.VerificationCodeV1;
import br.com.central.erros.impl.business.exception.exceptions.ObjectNotFoundException;
import br.com.central.erros.impl.business.service.V1.contracts.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class EmailServiceImplV1 implements EmailService {

    private static final String EMAIL_INVALIDO = "O e-mail inserido não pertence a nenhum usuário cadastrado!";

    @Value("${default.sender}")
    private String sender;

    @Autowired
    private VerificationCodeServiceImplV1 verificationCodeService;

    @Autowired
    private UserServiceImplV1 userService;

    @Autowired
    private MailSender mailSender;

    public void sendVerificationCode(String email) {
        if(userService.existsByEmail(email)) {
            VerificationCodeV1 code = verificationCodeService.create(email);
            sendMessage(prepareMessage(email, code.getToken()));
        } else {
            throw new ObjectNotFoundException(EMAIL_INVALIDO);
        }
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

    private void sendMessage(SimpleMailMessage sm) {
        mailSender.send(sm);
    }
}
