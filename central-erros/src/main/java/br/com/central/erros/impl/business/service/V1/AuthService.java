package br.com.central.erros.impl.business.service.V1;


import br.com.central.erros.impl.business.entity.V1.UserV1;
import br.com.central.erros.impl.business.exception.exceptions.ObjectNotFoundException;
import br.com.central.erros.impl.business.repository.V1.UserRepository;
import br.com.central.erros.impl.business.service.V1.contracts.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder pe;

    @Autowired
    private EmailService emailService;

    private Random random = new Random();


    public void sendNewPassword(String email) {

        UserV1 userV1 = userRepository.findByEmail(email);
        if (userV1 == null) {
            throw new ObjectNotFoundException("Email não encontrado");
        }

        String newPass = newPassword();
        userV1.setSenha(pe.encode(newPass));

        userRepository.save(userV1);
        emailService.sendNewPasswordEmail(userV1, newPass);

    }

    private String newPassword() {
        char[] vet = new char[10];
        for (int i = 0; i < 10; i++) {
            vet[i] = randomChar();
        }

        return new String(vet);
    }

    private char randomChar() {
        int opt = random.nextInt(3);
        if (opt == 0) {
            return (char) (random.nextInt(10) + 48);

        } else if (opt == 1) {
            return (char) (random.nextInt(26) + 65);

        } else {
            return (char) (random.nextInt(26) + 97);

        }
    }

    @Bean
    public EmailService emailService() {
        return new SmtpEmailService();
    }
}
