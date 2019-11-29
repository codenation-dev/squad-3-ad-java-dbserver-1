package br.com.central.erros.impl.business.service.V1;

import br.com.central.erros.impl.business.entity.V1.UserV1;
import br.com.central.erros.impl.business.service.V1.contracts.EmailService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import java.util.Date;


public abstract class AbstractEmailService implements EmailService {

    @Value("${default.sender}")
    private String sender;


    @Override
    public void sendNewPasswordEmail(UserV1 userV1, String newPass) {
        SimpleMailMessage sm = prepareNewPasswordEmail(userV1, newPass);
        sendEmail(sm);
    }

    protected SimpleMailMessage prepareNewPasswordEmail(UserV1 userV1, String newPass) {
        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setTo(userV1.getEmail());
        sm.setFrom(sender);
        sm.setSubject("Solicitação de nova senha");
        sm.setSentDate(new Date(System.currentTimeMillis()));
        sm.setText("Nova senha: " + newPass);
        return sm;
    }
}
