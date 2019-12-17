package br.com.central.erros.impl.business.entity.converter;

import br.com.central.erros.impl.business.dto.UserDTOV1;
import br.com.central.erros.impl.business.entity.V1.UserV1;
import br.com.central.erros.impl.business.entity.enums.Profile;
import br.com.central.erros.impl.business.exception.UserExceptionMessage;

import java.util.stream.Collectors;


public class UserConverter extends Converter {

    public static UserV1 userDTOToEntity(UserDTOV1 userDTO) {
        throwExceptionIfNull(userDTO, UserExceptionMessage.INVALID_DATA);
        UserV1 entity = new UserV1(userDTO.getId(), userDTO.getName(), userDTO.getEmail(), userDTO.getCpfOrCnpj(),
                userDTO.getType(), userDTO.getPassword());
        entity.setProfile(userDTO.getProfiles());
        return entity;
    }


    public static UserDTOV1 userToDTO(UserV1 user) {
        throwExceptionIfNull(user, UserExceptionMessage.INVALID_DATA);
        UserDTOV1 dto = new UserDTOV1(user.getId(), user.getName(), user.getEmail(), user.getCpfOrCnpj(), user.getType(),
                user.getPassword());
        dto.setProfiles(user.getProfile().stream().map(Profile::getCod).collect(Collectors.toSet()));
        return dto;
    }

}
