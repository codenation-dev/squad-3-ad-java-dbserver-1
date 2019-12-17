package br.com.central.erros.impl.business.service.V1;

import br.com.central.erros.impl.business.dto.UserDTOV1;
import br.com.central.erros.impl.business.dto.VerificationCodeDTO;
import br.com.central.erros.impl.business.entity.enums.UserType;
import br.com.central.erros.impl.business.exception.exceptions.ValidationException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

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
        when(verificationCodeService.isValid(any())).thenReturn(Boolean.FALSE);
        authService.updateUserPassword(dto, "newPassword");
    }

    @Test
    public void updatesUserPasswordAndDeletesToken() {
        UserDTOV1 userDTO = new UserDTOV1("John", "a@mail.com", "123", UserType.PESSOAFISICA, "oldPassword");
        VerificationCodeDTO verificationCodeDTO = new VerificationCodeDTO("abcd", userDTO.getEmail());
        when(verificationCodeService.isValid(any())).thenReturn(Boolean.TRUE);
        when(userService.findByEmail("a@mail.com")).thenReturn(userDTO);
        when(userService.save(any(UserDTOV1.class))).thenAnswer(invocation -> invocation.getArgument(0));
        ArgumentCaptor<UserDTOV1> captor = ArgumentCaptor.forClass(UserDTOV1.class);
        authService.updateUserPassword(verificationCodeDTO, "newPassword");
        verify(userService, times(1)).save(captor.capture());
        UserDTOV1 actual = captor.getValue();
        verify(verificationCodeService, times(1)).delete(anyString());
        Assert.assertEquals("newPassword", actual.getPassword());
    }

}
