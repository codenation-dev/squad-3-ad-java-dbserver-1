package br.com.central.erros.impl.business.service.V1;

import br.com.central.erros.impl.business.dto.VerificationCodeDTO;
import br.com.central.erros.impl.business.entity.V1.VerificationCodeV1;
import br.com.central.erros.impl.business.repository.V1.VerificationCodeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class VerificationCodeServiceImplV1Test {

    @Mock
    private VerificationCodeRepository repository;
    @InjectMocks
    private VerificationCodeServiceImplV1 service;

    @Test
    public void createsVerificationCode() {
        final String userEmail = "user@mailing.com";
        Mockito.when(repository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));
        VerificationCodeV1 code = service.create(userEmail);
        assertThat(code.getEmail()).isEqualTo(userEmail);
        assertThat(code.getToken()).containsOnlyDigits();
        assertEquals(4, code.getToken().length());
    }

    @Test
    public void returnsFalseWhenTryingToValidateNonexistentToken() {
        final VerificationCodeDTO dto = new VerificationCodeDTO("1234", "user@mailing.com");
        Mockito.when(repository.findByToken(dto.getToken())).thenReturn(Optional.empty());
        assertFalse(service.isValid(dto));
    }

    @Test
    public void returnsFalseWhenTryingToValidateOtherUsersToken() {
        final VerificationCodeDTO dto = new VerificationCodeDTO("1234", "user@mailing.com");
        final VerificationCodeV1 repositoryReturn = new VerificationCodeV1("1234","user@personal.com");
        Mockito.when(repository.findByToken(dto.getToken())).thenReturn(Optional.of(repositoryReturn));
        assertFalse(service.isValid(dto));
    }

    @Test
    public void returnsTrueWhenTryingToValidateActualToken() {
        final VerificationCodeDTO dto = new VerificationCodeDTO("1234", "user@mailing.com");
        final VerificationCodeV1 repositoryReturn = new VerificationCodeV1("1234","user@mailing.com");
        Mockito.when(repository.findByToken(dto.getToken())).thenReturn(Optional.of(repositoryReturn));
        assertTrue(service.isValid(dto));
    }

    @Test
    public void deletesVerificationToken() {
        service.delete("hello@123.com");
        Mockito.verify(repository, times(1)).deleteByEmail(any());
    }
}
