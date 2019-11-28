package br.com.central.erros.impl.config;

import br.com.central.erros.impl.business.service.V1.SmtpEmailService;
import br.com.central.erros.impl.business.service.V1.contracts.EmailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

//@Profile("dev")

@Configuration
public class DevConfig {


  /*  public EmailService emailService() {

        return new SmtpEmailService();
    }*/
}