package br.com.central.erros.impl.business.service.V1;

import java.util.List;
import java.util.stream.Collectors;

import br.com.central.erros.impl.business.dto.UserDTOV1;
import br.com.central.erros.impl.business.entity.V1.UserV1;
import br.com.central.erros.impl.business.entity.converter.UserConverter;
import br.com.central.erros.impl.business.repository.V5.UserRepositoryV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplV1 implements UserServiceV1 {

    private UserRepositoryV1 userRepositoryV1;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImplV1(UserRepositoryV1 userRepositoryV1, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepositoryV1 = userRepositoryV1;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public List<UserDTOV1> buscaUsersList() {
        List<UserV1> listaFuncionarios = userRepositoryV1.findAll();

        List<UserDTOV1> listFuncionarioDTOV5 =
                listaFuncionarios.stream().map(UserConverter::toDTOV1).collect(Collectors.toList());

        return listFuncionarioDTOV5;
    }

    @Override
    public UserDTOV1 salvarNovoUSuario(UserDTOV1 userDTOV1) {

        String senhaEncode = bCryptPasswordEncoder.encode(userDTOV1.getSenha());
        userDTOV1.setSenha(senhaEncode);

        UserV1 usuarioEntity = UserConverter.toEntity(userDTOV1);

        UserV1 usuarioSalvoNoBanco = userRepositoryV1.save(usuarioEntity);

        UserDTOV1 userDTOV11 = UserConverter.toDTOV1(usuarioSalvoNoBanco);

        return userDTOV1;
    }


}
