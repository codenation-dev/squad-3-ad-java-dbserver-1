package br.com.central.erros.impl.business.service.V1;

import br.com.central.erros.impl.business.dto.VerificationCodeDTO;
import br.com.central.erros.impl.business.exception.exceptions.ValidationException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class AuthServiceImplV1Test {
    @Mock
    private UserServiceImplV1 userService;

    @Mock
    private VerificationCodeServiceImplV1 verificationCodeService;

    @InjectMocks
    private AuthServiceImplV1 authService;

    @Test(expected = ValidationException.class)
    public void returnsExceptionIfCodeIsInvalid() {
        final VerificationCodeDTO dto = new VerificationCodeDTO("1234", "hello@mail.com");
        Mockito.when(verificationCodeService.isValid(any())).thenReturn(false);
        authService.updateUserPassword(dto, "newPassword");
    }

    /*@Test
    public void updatesUserPasswordAndDeletesToken() {
        final VerificationCodeDTO code = new VerificationCodeDTO("1234", "a@mail.com");
        final UserDTOV1 user = new UserDTOV1(1, "User", "a@mail.com", "1234", UserType.PESSOAFISICA, "old");
        Mockito.when(userService.findByEmail(code.getEmail())).thenReturn(user);
        Mockito.when(verificationCodeService.isValid(any())).thenReturn(true);
        Mockito.when(userService.save(any())).thenAnswer(invocation -> invocation.getArgument(0));
        Mockito.verify(userService, times(1)).save(argThat(argument -> argument.getPassword().equals("newPasword")));
        Mockito.verify(verificationCodeService, times(1)).delete(code.getEmail());
        authService.updateUserPassword(code, "newPassword");
    }*/
}
