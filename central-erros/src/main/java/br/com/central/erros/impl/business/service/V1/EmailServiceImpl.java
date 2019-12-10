package br.com.central.erros.impl.business.service.V1;

import br.com.central.erros.impl.business.entity.V1.UserV1;
import br.com.central.erros.impl.business.entity.V1.VerificationCode;
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
    private static final Logger LOG = LoggerFactory.getLogger(SmtpEmailService.class);

    @Value("${default.sender}")
    private String sender;

    @Autowired
    private VerificationCodeService verificationCodeService;

    @Autowired
    private UserServiceImplV1 userService;

    @Autowired
    private MailSender mailSender;

    public void sendEmail(SimpleMailMessage msg) {
        LOG.info("Enviando email...");
        mailSender.send(msg);
        LOG.info("Email enviado");
    }

    public void sendVerificationCode(UserV1 user) {
        if(userService.existeUsuarioComEmail(user.getEmail())) {
            VerificationCode code = verificationCodeService.create(user);
            sendMessage(user.getEmail(), code.getToken());
        } else {
            throw new ObjectNotFoundException(EMAIL_INVALIDO);
        }
    }

    private void sendMessage(String email, String verificationCode) {
        SimpleMailMessage sm = prepareMessage(email, verificationCode);
        sendEmail(sm);
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
