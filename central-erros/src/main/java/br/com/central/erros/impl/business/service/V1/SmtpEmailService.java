package br.com.central.erros.impl.business.service.V1;

import br.com.central.erros.impl.business.service.V1.contracts.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

public class SmtpEmailService {

    private final MailSender mailSender;

    private static final Logger LOG = LoggerFactory.getLogger(SmtpEmailService.class);

    public SmtpEmailService(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(SimpleMailMessage msg) {
        LOG.info("Enviando email...");
        mailSender.send(msg);
        LOG.info("Email enviado");
    }
}
