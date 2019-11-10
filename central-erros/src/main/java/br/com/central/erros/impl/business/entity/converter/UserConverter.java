package br.com.central.erros.impl.business.entity.converter;

import java.util.Objects;

import br.com.central.erros.impl.business.dto.UserDTOV1;
import br.com.central.erros.impl.business.entity.V1.UserV1;
import br.com.central.erros.impl.business.exception.UserExceptionMessage;
import br.com.central.erros.impl.business.exception.exceptions.ValidationException;


public class UserConverter {


    public static UserV1 toEntity(UserDTOV1 userDTOV1) {
        if (Objects.isNull(userDTOV1)) {
            throw new ValidationException(UserExceptionMessage.ERROR_DADOS_INVALIDOS_USER);
        }
        return new UserV1.Builder()
                .id(userDTOV1.getId())
                .nome(userDTOV1.getNome())
                .email(userDTOV1.getEmail())
                .senha(userDTOV1.getSenha())
                .token(userDTOV1.getToken())
                .build();
    }


    public static UserDTOV1 toDTOV1(UserV1 userV1){
        if(Objects.isNull(userV1)){
            return null;
        }
        return new UserDTOV1.Builder()
                .id(userV1.getId())
                .nome(userV1.getNome())
                .email(userV1.getEmail())
                .senha(userV1.getSenha())
                .token(userV1.getToken())
                .build();
    }

}
