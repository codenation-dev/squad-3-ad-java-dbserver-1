package br.com.central.erros.impl.business.entity.converter;

import java.util.Objects;
import java.util.stream.Collectors;

import br.com.central.erros.impl.business.dto.UserDTOV1;
import br.com.central.erros.impl.business.entity.V1.UserV1;
import br.com.central.erros.impl.business.entity.enums.Perfil;
import br.com.central.erros.impl.business.exception.UserExceptionMessage;
import br.com.central.erros.impl.business.exception.exceptions.ValidationException;


public class UserConverter {

    private static void jogarExcessaoSeUsuarioNulo(Object usuario) {
        if (Objects.isNull(usuario)) {
            throw new ValidationException(UserExceptionMessage.ERRO_DADOS_DO_USUARIO_INVALIDOS);
        }
    }

    public static UserV1 userDTOToEntity(UserDTOV1 userDTO) {
        jogarExcessaoSeUsuarioNulo(userDTO);
        return new UserV1(null, userDTO.getNome(), userDTO.getEmail(), userDTO.getCpfOuCnpj(),
                userDTO.getTipo(), userDTO.getSenha());
    }


    public static UserDTOV1 userToDTO(UserV1 user) {
        jogarExcessaoSeUsuarioNulo(user);
        UserDTOV1 dto = new UserDTOV1(user.getNome(), user.getEmail(), user.getCpfOuCnpj(), user.getTipo(),
                user.getSenha());
        dto.setPerfis(user.getPerfis().stream().map(Perfil::getCod).collect(Collectors.toSet()));
        return dto;
    }

}
