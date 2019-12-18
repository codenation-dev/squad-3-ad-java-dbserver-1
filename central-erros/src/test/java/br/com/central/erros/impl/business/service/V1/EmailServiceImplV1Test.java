package br.com.central.erros.impl.business.service.V1;

import br.com.central.erros.impl.business.entity.V1.VerificationCodeV1;
import br.com.central.erros.impl.business.exception.exceptions.ObjectNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mail.MailSender;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EmailServiceImplV1Test {
    @Mock
    private VerificationCodeServiceImplV1 verificationCodeService;

    @Mock
    private UserServiceImplV1 userService;

    @Mock
    private MailSender mailSender;

    @InjectMocks
    private EmailServiceImplV1 emailService;

    private final String targetEmail = "your@mail.com";

    @Test(expected = ObjectNotFoundException.class)
    public void throwsExceptionWhenUserDoesNotExist() {
        when(userService.existsByEmail(targetEmail)).thenReturn(false);
        emailService.sendVerificationCode(targetEmail);
    }

    @Test
    public void sendsVerificationCode() {
        VerificationCodeV1 code = new VerificationCodeV1("1234", targetEmail);
        when(userService.existsByEmail(targetEmail)).thenReturn(true);
        when(verificationCodeService.create(targetEmail)).thenReturn(code);
        emailService.sendVerificationCode(targetEmail);
    }
}
