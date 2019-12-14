package br.com.central.erros.impl.business.service.V1;

import br.com.central.erros.impl.business.dto.VerificationCodeDTO;
import br.com.central.erros.impl.business.entity.V1.VerificationCode;
import br.com.central.erros.impl.business.repository.V1.VerificationCodeRepository;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class VerificationCodeServiceImplV1Test {
    @Mock
    private VerificationCodeRepository verificationCodeRepository;

    @InjectMocks
    private VerificationCodeServiceImplV1 verificationCodeService;

    @Test
    public void salvaCodigo() {
        VerificationCode expected = new VerificationCode("1234", "usuario@mail.com");
        when(verificationCodeRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));
        VerificationCode actual = verificationCodeService.create("usuario@mail.com");
        assertThat(expected).isEqualToIgnoringGivenFields(verificationCodeService.create("usuario@mail.com"),
                "token");
        Assert.assertEquals(4, actual.getToken().length());
    }

    @Test
    public void retornaFalsoQuandoCodigoInseridoNaoExiste() {
        VerificationCodeDTO code = new VerificationCodeDTO("1234", "email@mail.com");
        when(verificationCodeRepository.findByToken("1234")).thenReturn(Optional.empty());
        Assert.assertEquals(Boolean.FALSE, verificationCodeService.isValid(code));
    }

    @Test
    public void retornaFalsoQuandoCodigoInseridoPertenceAOutroUsuario() {
        VerificationCodeDTO code = new VerificationCodeDTO("1234", "email@mail.com");
        VerificationCodeDTO repositoryResult = new VerificationCodeDTO("1234", "outromail@mail.com");
        when(verificationCodeRepository.findByToken("1234")).thenReturn(Optional.empty());
        Assert.assertEquals(Boolean.FALSE, verificationCodeService.isValid(code));
    }
}
