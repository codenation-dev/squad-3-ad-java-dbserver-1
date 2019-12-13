package br.com.central.erros.impl.business.service.V1;

import br.com.central.erros.impl.business.dto.UserDTOV1;
import br.com.central.erros.impl.business.entity.V1.UserV1;
import br.com.central.erros.impl.business.entity.converter.UserConverter;
import br.com.central.erros.impl.business.entity.enums.TipoUser;
import br.com.central.erros.impl.business.exception.exceptions.ObjectNotFoundException;
import br.com.central.erros.impl.business.repository.V1.UserRepository;
import org.assertj.core.api.Assertions;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThat;
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
                "123", TipoUser.PESSOAFISICA, "não encriptada");
        
        final UserDTOV1 expected = new UserDTOV1(actualDto.getNome(), actualDto.getEmail(),
                actualDto.getCpfOuCnpj(), actualDto.getTipo(), "encriptada");
        when(bCryptPasswordEncoder.encode(actualDto.getSenha())).thenReturn("encriptada");

        final UserV1 entity = new UserV1(null, actualDto.getNome(), actualDto.getEmail(), actualDto.getCpfOuCnpj(), actualDto.getTipo(), actualDto.getSenha());
        when(userRepositoryV1.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        UserDTOV1 actual = userService.salvar(actualDto);

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
