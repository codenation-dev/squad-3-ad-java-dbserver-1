package br.com.central.erros.impl.entity.converter;

import br.com.central.erros.impl.business.dto.UserDTOV1;
import br.com.central.erros.impl.business.entity.V1.UserV1;
import br.com.central.erros.impl.business.entity.converter.UserConverter;
import br.com.central.erros.impl.business.entity.enums.TipoUser;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class UserConverterTest {

    @Test
    public void convertsFromDTOToEntity() {
        UserV1 expected = new UserV1(null, "Mario", "m@ario.com", "123", TipoUser.PESSOAFISICA,
                "123");
        UserDTOV1 dto = new UserDTOV1(expected.getNome(), expected.getEmail(), expected.getCpfOuCnpj(),
                expected.getTipo(), expected.getSenha());
        assertThat(expected).isEqualToComparingFieldByFieldRecursively(UserConverter.userDTOToEntity(dto));
    }

    @Test
    public void convertsFromEntityToDTO() {
        UserDTOV1 expected = new UserDTOV1("Mario", "m@ario.com", "123", TipoUser.PESSOAFISICA,
                "123");
        UserV1 entity = new UserV1(null, expected.getNome(), expected.getEmail(), expected.getCpfOuCnpj(),
                expected.getTipo(), expected.getSenha());
        assertThat(expected).isEqualToComparingFieldByFieldRecursively(UserConverter.userToDTO(entity));
    }
}
