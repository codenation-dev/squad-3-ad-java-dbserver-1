package br.com.central.erros.impl.business.service.V1.contracts;

import br.com.central.erros.impl.business.entity.V1.UserV1;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {
    void sendEmail(SimpleMailMessage msg);
    void sendVerificationCode(UserV1 user);
}

