package br.com.central.erros.impl.api.V1.contracts;


import java.util.List;
import java.util.Optional;

import br.com.central.erros.impl.business.dto.EmailDTO;
import br.com.central.erros.impl.business.dto.UserDTOV1;
import org.springframework.http.ResponseEntity;

public interface UserRestEndpointV1 {

    ResponseEntity<List<UserDTOV1>> buscaUsersList();

    ResponseEntity<Void> adicionaUser(UserDTOV1 userDTOV1);

    ResponseEntity<UserDTOV1> buscaUser(Integer id);

    ResponseEntity<Void> atualizaUser( UserDTOV1 userDTOV1, Integer id);

    ResponseEntity<Void> editarSenhaUser(String email, String novaSenha);

   // ResponseEntity<Optional<UserDTOV1>> buscaUsersById(Integer id);

    ResponseEntity<Void> recuperaSenhaUser( EmailDTO objDto);




}
