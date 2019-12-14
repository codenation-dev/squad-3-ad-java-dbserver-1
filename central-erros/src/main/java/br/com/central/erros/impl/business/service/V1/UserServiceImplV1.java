package br.com.central.erros.impl.business.service.V1;

import java.util.List;
import java.util.stream.Collectors;

import br.com.central.erros.impl.business.dto.UserDTOV1;
import br.com.central.erros.impl.business.entity.V1.UserV1;
import br.com.central.erros.impl.business.entity.converter.UserConverter;
import br.com.central.erros.impl.business.entity.enums.Perfil;
import br.com.central.erros.impl.business.exception.exceptions.AuthorizationException;
import br.com.central.erros.impl.business.exception.exceptions.ObjectNotFoundException;
import br.com.central.erros.impl.business.repository.V1.UserRepository;
import br.com.central.erros.impl.business.service.V1.contracts.UserServiceV1;
import br.com.central.erros.impl.config.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplV1 implements UserServiceV1 {


    private UserRepository userRepositoryV1;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImplV1(UserRepository userRepositoryV1, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepositoryV1 = userRepositoryV1;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public List<UserDTOV1> buscarTodos() {
        UserSS user = authenticated();
        if (user == null || !user.hasRole(Perfil.ADMIN)) {
            throw new AuthorizationException("Acesso negado");
        }
        List<UserV1> listaUsuarios = userRepositoryV1.findAll();
        return listaUsuarios.stream().map(UserConverter::userToDTO).collect(Collectors.toList());
    }

    @Override
    public UserDTOV1 findById(Integer id) {
        UserSS user = authenticated();
        if (user == null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
            throw new AuthorizationException("Acesso negado");
        }
        UserV1 usuario = userRepositoryV1.findById(id).orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + UserV1.class.getName()));;
        return UserConverter.userToDTO(usuario);
    }

    public UserDTOV1 findByEmail(String email) {
        UserV1 user = userRepositoryV1.findByEmail(email)
                .orElseThrow(() -> new ObjectNotFoundException(
                        "Objeto não encontrado! E-mail: " + email + ", Tipo: " + UserV1.class.getName()));;
        return UserConverter.userToDTO(user);
    }

    @Override
    public UserDTOV1 salvar(UserDTOV1 userInput) {
        String senhaEncode = bCryptPasswordEncoder.encode(userInput.getSenha());
        userInput.setSenha(senhaEncode);
        UserV1 usuarioEntity = UserConverter.userDTOToEntity(userInput);
        UserV1 usuarioSalvoNoBanco = userRepositoryV1.save(usuarioEntity);
        return UserConverter.userToDTO(usuarioSalvoNoBanco);
    }

    public boolean existeUsuarioComEmail(String email) {
        return userRepositoryV1.existsByEmail(email);
    }

    private static UserSS authenticated() {
        try {
            return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            return null;
        }
    }


}
