package br.com.central.erros.impl.business.entity.converter;

import br.com.central.erros.impl.business.dto.UserDTOV1;
import br.com.central.erros.impl.business.entity.V1.UserV1;
import br.com.central.erros.impl.business.entity.converter.UserConverter;
import br.com.central.erros.impl.business.entity.enums.TipoUser;
import br.com.central.erros.impl.business.exception.exceptions.ValidationException;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserConverterTest {

    @Test
    public void converteDeDTOParaEntidade() {
        UserV1 expected = new UserV1(null, "Mario", "m@ario.com", "123", TipoUser.PESSOAFISICA,
                "123");
        UserDTOV1 dto = new UserDTOV1(expected.getNome(), expected.getEmail(), expected.getCpfOuCnpj(),
                expected.getTipo(), expected.getSenha());
        assertThat(expected).isEqualToComparingFieldByFieldRecursively(UserConverter.userDTOToEntity(dto));
    }

    @Test
    public void converteDeEntidadeParaDTO() {
        UserDTOV1 expected = new UserDTOV1("Mario", "m@ario.com", "123", TipoUser.PESSOAFISICA,
                "123");
        UserV1 entity = new UserV1(null, expected.getNome(), expected.getEmail(), expected.getCpfOuCnpj(),
                expected.getTipo(), expected.getSenha());
        assertThat(expected).isEqualToComparingFieldByFieldRecursively(UserConverter.userToDTO(entity));
    }

    @Test(expected = ValidationException.class)
    public void retornaExcessaoQuandoTentaConverterUsuarioNuloParaDTO() {
        UserConverter.userToDTO(null);
    }

    @Test(expected = ValidationException.class)
    public void retornaExcessaoQuandoTentaConverterDTONuloParaUsuario() {
        UserConverter.userDTOToEntity(null);
    }
}
