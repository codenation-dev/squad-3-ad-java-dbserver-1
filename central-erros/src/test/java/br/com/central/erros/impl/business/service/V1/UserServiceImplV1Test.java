package br.com.central.erros.impl.business.service.V1;

import br.com.central.erros.impl.business.dto.UserDTOV1;
import br.com.central.erros.impl.business.entity.V1.UserV1;
import br.com.central.erros.impl.business.entity.converter.UserConverter;
import br.com.central.erros.impl.business.entity.enums.Profile;
import br.com.central.erros.impl.business.entity.enums.UserType;
import br.com.central.erros.impl.business.exception.exceptions.AuthorizationException;
import br.com.central.erros.impl.business.exception.exceptions.ObjectNotFoundException;
import br.com.central.erros.impl.business.repository.V1.UserRepository;
import br.com.central.erros.impl.config.security.UserSS;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class UserServiceImplV1Test {

    @Mock
    private UserRepository userRepositoryV1;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Mock
    private Authentication auth;

    @Mock
    private SecurityContext securityContext;

    @InjectMocks
    private UserServiceImplV1 userService;

    private UserSS adminUser() {
        Set<Profile> userProfiles = new HashSet<>();
        userProfiles.add(Profile.ADMIN);
        return new UserSS(1,"he@ya.com", "123", userProfiles);
    }

    private UserSS customerUser() {
        Set<Profile> userProfiles = new HashSet<>();
        userProfiles.add(Profile.CLIENTE);
        return new UserSS(2, "he@ya.com", "123", userProfiles);
    }

    private void mockSpringSecurityUser(UserSS user) {
        when(securityContext.getAuthentication()).thenReturn(auth);
        SecurityContextHolder.setContext(securityContext);
        when(SecurityContextHolder.getContext().getAuthentication().getPrincipal()).thenReturn(user);
    }

    @Test
    public void findsAllUsers() {
        final List<UserV1> userList = new ArrayList<>();
        final UserV1 user = new UserV1(0, "João", "abc@123.com", "123", UserType.PESSOAFISICA, "$2$546");
        userList.add(user);
        when(userRepositoryV1.findAll()).thenReturn(userList);
        List<UserDTOV1> actual = userService.findAll();
        assertThat(actual, contains(hasProperty("name", Matchers.is("João"))));
    }

    @Test
    public void savesUser() {
        final UserDTOV1 actualDto = new UserDTOV1("João", "abc@123.com", "123", UserType.PESSOAFISICA, "não encriptada");

        final UserDTOV1 expected = new UserDTOV1(actualDto.getName(), actualDto.getEmail(), actualDto.getCpfOrCnpj(), actualDto.getType(), "encriptada");
        when(bCryptPasswordEncoder.encode(actualDto.getPassword())).thenReturn("encriptada");

        final UserV1 entity = new UserV1(null, actualDto.getName(), actualDto.getEmail(), actualDto.getCpfOrCnpj(), actualDto.getType(), actualDto.getPassword());
        when(userRepositoryV1.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        UserDTOV1 actual = userService.save(actualDto);

        assertThat(actual).isEqualToComparingFieldByField(expected);

        verify(userRepositoryV1).save(entity);
    }

    @Test
    public void findsCustomerByTheirOwnId() {
        mockSpringSecurityUser(customerUser());
        final UserDTOV1 expected = new UserDTOV1("João", "abc@123.com", "123", UserType.PESSOAFISICA, "$2$546");
        when(userRepositoryV1.findById(2)).thenReturn(Optional.of(UserConverter.userDTOToEntity(expected)));
        userService.findById(2);
    }

    @Test(expected = AuthorizationException.class)
    public void throwsExceptionWhenTriesToFindByIdButNotAuthenticated() {
        userService.findById(1);
    }

    @Test(expected = AuthorizationException.class)
    public void throwsExceptionWhenCustomerTriesToFindSomeoneElseById() {
        mockSpringSecurityUser(customerUser());
        userService.findById(1);
    }

    @Test
    public void findsAnyUserByIdWhenAdmin() {
        mockSpringSecurityUser(adminUser());
        final UserDTOV1 expected = new UserDTOV1("João", "abc@123.com", "123", UserType.PESSOAFISICA, "$2$546");
        when(userRepositoryV1.findById(1)).thenReturn(Optional.of(UserConverter.userDTOToEntity(expected)));

        final UserDTOV1 actual = userService.findById(1);
        assertThat(expected).isEqualToComparingFieldByField(actual);
    }

    @Test
    public void findsValidUserByEmail() {
        final UserDTOV1 expected = new UserDTOV1("João", "abc@123.com", "123", UserType.PESSOAFISICA, "$2$546");
        when(userRepositoryV1.findByEmail("abc@123.com")).thenReturn(Optional.of(UserConverter.userDTOToEntity(expected)));
        final UserDTOV1 actual = userService.findByEmail("abc@123.com");
        assertThat(expected).isEqualToComparingFieldByField(actual);
    }

    @Test
    public void returnsFalseWhenRequestedEmailDoesNotExist() {
        when(userRepositoryV1.existsByEmail("abc@123.com")).thenReturn(false);
        Assert.assertFalse(userService.existsByEmail("abc@123.com"));
    }

    @Test
    public void returnsTrueWhenRequestedEmailExists() {
        when(userRepositoryV1.existsByEmail("abc@123.com")).thenReturn(true);
        Assert.assertTrue(userService.existsByEmail("abc@123.com"));
    }

    @Test(expected = ObjectNotFoundException.class)
    public void throwsExceptionWhenInvalidUserRequestedById() {
        mockSpringSecurityUser(adminUser());
        when(userRepositoryV1.findById(1)).thenReturn(Optional.empty());
        userService.findById(1);
    }

    @Test(expected = ObjectNotFoundException.class)
    public void throwsExceptionWhenInvalidUserRequestedByEmail() {
        when(userRepositoryV1.findByEmail("abc@123.com")).thenReturn(Optional.empty());
        userService.findByEmail("abc@123.com");
    }
}