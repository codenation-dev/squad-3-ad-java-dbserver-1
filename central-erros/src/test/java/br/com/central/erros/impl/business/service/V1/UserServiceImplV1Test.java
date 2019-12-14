package br.com.central.erros.impl.business.service.V1;

import br.com.central.erros.impl.business.dto.UserDTOV1;
import br.com.central.erros.impl.business.entity.V1.UserV1;
import br.com.central.erros.impl.business.entity.enums.UserType;
import br.com.central.erros.impl.business.repository.V1.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplV1Test {

    @Mock
    private UserRepository userRepositoryV1;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @InjectMocks
    private UserServiceImplV1 userService;

    /*@Test
    public void retornaListaDeUsuarios() {
        final List<UserV1> listaDeUsuarios = new ArrayList<>();
        final UserV1 usuario = new UserV1(0, "João", "abc@123.com",
                "123", TipoUser.PESSOAFISICA, "$2$546");
        listaDeUsuarios.add(usuario);
        when(userRepositoryV1.findAll()).thenReturn(listaDeUsuarios);

        List<UserDTOV1> actual = userService.buscaUsersList();

        assertThat(actual, contains(
                hasProperty("nome", Matchers.is("João"))
        ));
    }*/

    @Test
    public void salvaUsuario() {
        final UserDTOV1 actualDto = new UserDTOV1("João", "abc@123.com",
                "123", UserType.PESSOAFISICA, "não encriptada");
        
        final UserDTOV1 expected = new UserDTOV1(actualDto.getName(), actualDto.getEmail(),
                actualDto.getCpfOrCnpj(), actualDto.getType(), "encriptada");
        when(bCryptPasswordEncoder.encode(actualDto.getPassword())).thenReturn("encriptada");

        final UserV1 entity = new UserV1(null, actualDto.getName(), actualDto.getEmail(), actualDto.getCpfOrCnpj(), actualDto.getType(), actualDto.getPassword());
        when(userRepositoryV1.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        UserDTOV1 actual = userService.save(actualDto);

        assertThat(actual).isEqualToComparingFieldByField(expected);

        verify(userRepositoryV1).save(entity);
    }

//    @Test
//    public void retornaUsuarioValido() {
//        final UserDTOV1 expected = new UserDTOV1("João", "abc@123.com",
//                "123", TipoUser.PESSOAFISICA, "$2$546");
//        when(userRepositoryV1.findById(1)).thenReturn(Optional.of(UserConverter.userDTOToEntity(expected)));
//
//        final UserDTOV1 actual = userService.findById(1);
//        assertThat(expected).isEqualToComparingFieldByField(actual);
//    }

//    @Test(expected = ObjectNotFoundException.class)
//    public void retornaExcessaoQuandoBuscaUsuarioInvalido() {
//        when(userRepositoryV1.findById(1)).thenReturn(Optional.empty());
//        userService.findById(1);
//    }
}
