package br.com.central.erros.impl.business.entity.converter;

import java.util.Objects;
import java.util.stream.Collectors;

import br.com.central.erros.impl.business.dto.UserDTOV1;
import br.com.central.erros.impl.business.entity.V1.UserV1;
import br.com.central.erros.impl.business.exception.UserExceptionMessage;
import br.com.central.erros.impl.business.exception.exceptions.ValidationException;


public class UserConverter {


    public static UserV1 userDTOToEntity(UserDTOV1 userDTOV1) {

        if (Objects.isNull(userDTOV1)) {
            throw new ValidationException(UserExceptionMessage.ERROR_DADOS_INVALIDOS_USER);
        }

        return new UserV1(null, userDTOV1.getNome(), userDTOV1.getEmail(), userDTOV1.getCpfOuCnpj(), userDTOV1.getTipo(), userDTOV1.getSenha());
    }


    public static UserDTOV1 userToDTO(UserV1 user) {

        if (Objects.isNull(user)) {
            throw new ValidationException(UserExceptionMessage.ERROR_DADOS_INVALIDOS_USER);
        }

        UserDTOV1 userDTOV1 = new UserDTOV1(user.getId(), user.getNome(), user.getEmail(), user.getCpfOuCnpj(), user.getTipo(), user.getSenha());
        userDTOV1.setPerfis(user.getPerfis().stream().map(value -> value.getCod()).collect(Collectors.toSet()));
        return userDTOV1;
    }

}
