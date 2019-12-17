package br.com.central.erros.impl.business.service.V1;

import br.com.central.erros.impl.business.dto.UserDTOV1;
import br.com.central.erros.impl.business.entity.V1.UserV1;
import br.com.central.erros.impl.business.entity.converter.UserConverter;
import br.com.central.erros.impl.business.entity.enums.Profile;
import br.com.central.erros.impl.business.exception.exceptions.AuthorizationException;
import br.com.central.erros.impl.business.exception.exceptions.ObjectNotFoundException;
import br.com.central.erros.impl.business.repository.V1.UserRepository;
import br.com.central.erros.impl.business.service.V1.contracts.UserServiceV1;
import br.com.central.erros.impl.config.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImplV1 implements UserServiceV1 {


    private final UserRepository userRepositoryV1;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImplV1(UserRepository userRepositoryV1, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepositoryV1 = userRepositoryV1;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public List<UserDTOV1> findAll() {
        List<UserV1> userList = userRepositoryV1.findAll();
        return userList.stream().map(UserConverter::userToDTO).collect(Collectors.toList());
    }

    @Override
    public UserDTOV1 findById(Integer id) {
        UserSS user = authenticated();
        if (user == null || !user.hasRole(Profile.ADMIN) && !id.equals(user.getId())) {
            throw new AuthorizationException("Acesso negado");
        }
        UserV1 usuario = userRepositoryV1.findById(id).orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + UserV1.class.getName()));
        return UserConverter.userToDTO(usuario);
    }

    public UserDTOV1 findByEmail(String email) {
        UserV1 user = userRepositoryV1.findByEmail(email)
                .orElseThrow(() -> new ObjectNotFoundException(
                        "Objeto não encontrado! E-mail: " + email + ", Tipo: " + UserV1.class.getName()));
        return UserConverter.userToDTO(user);
    }

    @Override
    public UserDTOV1 save(UserDTOV1 userInput) {
        String senhaEncode = bCryptPasswordEncoder.encode(userInput.getPassword());
        userInput.setPassword(senhaEncode);
        UserV1 usuarioEntity = UserConverter.userDTOToEntity(userInput);
        UserV1 usuarioSalvoNoBanco = userRepositoryV1.save(usuarioEntity);
        return UserConverter.userToDTO(usuarioSalvoNoBanco);
    }

    public UserV1 fromDTO(UserDTOV1 userDTOV1) {
        return new UserV1(userDTOV1.getId(), userDTOV1.getName(), userDTOV1.getEmail(), null, null, null);
    }

    public boolean existsByEmail(String email) {
        return userRepositoryV1.existsByEmail(email);
    }

    private static UserSS authenticated() {
        try {
            return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            return null;
        }
    }


    public UserV1 update(UserV1 userV1) {
        UserDTOV1 newUserV1 = findById(userV1.getId());
        updateData(newUserV1, userV1);
        UserV1 usuarioEntity = UserConverter.userDTOToEntity(newUserV1);
        return userRepositoryV1.save(usuarioEntity);
    }

    private void updateData(UserDTOV1 newObj, UserV1 obj) {
        newObj.setName(obj.getName());
        newObj.setEmail(obj.getEmail());
    }
}
