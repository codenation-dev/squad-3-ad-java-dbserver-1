package br.com.central.erros.impl.business.service.V1;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.com.central.erros.impl.business.dto.UserDTOV1;
import br.com.central.erros.impl.business.entity.V1.UserV1;
import br.com.central.erros.impl.business.entity.converter.UserConverter;
import br.com.central.erros.impl.business.exception.exceptions.ObjectNotFoundException;
import br.com.central.erros.impl.business.repository.V1.UserRepository;
import br.com.central.erros.impl.business.service.V1.contracts.UserServiceV1;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<UserDTOV1> buscaUsersList() {
        List<UserV1> listaFuncionarios = userRepositoryV1.findAll();

        List<UserDTOV1> listFuncionarioDTOV5 =
                listaFuncionarios.stream().map(UserConverter::userToDTO).collect(Collectors.toList());

        return listFuncionarioDTOV5;
    }

    @Override
    public UserDTOV1 findById(Integer id){

//		UserSS user = UserServiceExcluir.authenticated();
//		if (user==null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
//			throw new AuthorizationException("Acesso negado");
//		}

        Optional<UserV1> obj = userRepositoryV1.findById(id);

        obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + UserV1.class.getName()));

        return UserConverter.userToDTO(obj.get());

    }

    @Override
    public Optional<UserDTOV1> buscaUsersById(Integer id) {
        Optional<UserV1> optionalUserV1 = userRepositoryV1.findById(id);

        return optionalUserV1.map(UserConverter::userToDTO);

    }

    @Override
    public UserDTOV1 salvarNovoUSuario(UserDTOV1 userInput) {

        String senhaEncode = bCryptPasswordEncoder.encode(userInput.getSenha());
        userInput.setSenha(senhaEncode);

        UserV1 usuarioEntity = UserConverter.userDTOToEntity(userInput);

        UserV1 usuarioSalvoNoBanco = userRepositoryV1.save(usuarioEntity);

        return UserConverter.userToDTO(usuarioSalvoNoBanco);
    }





}
