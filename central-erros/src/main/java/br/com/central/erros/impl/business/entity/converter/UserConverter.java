package br.com.central.erros.impl.business.entity.converter;

import java.util.Objects;

import br.com.central.erros.impl.business.dto.UserDTOV1;
import br.com.central.erros.impl.business.entity.V1.UserV1;
import br.com.empresa.impl.business.dto.*;
import br.com.empresa.impl.business.entity.V1.Funcionario;
import br.com.empresa.impl.business.entity.V2.FuncionarioV2;
import br.com.empresa.impl.business.entity.V3.FuncionarioV3;
import br.com.empresa.impl.business.entity.V4.FuncionarioV4;
import br.com.empresa.impl.business.entity.V5.FuncionarioV5;
import br.com.empresa.impl.business.exception.FuncionarioExceptionMessage;
import br.com.empresa.impl.business.exception.exceptions.ValidationException;


public class UserConverter {

    public static Funcionario toEntity(FuncionarioDTOV1 funcionarioDTOV1) {
        if (Objects.isNull(funcionarioDTOV1)) {
            throw new ValidationException(FuncionarioExceptionMessage.ERRO_DADOS_INVALIDOS_ASSOCIADO);
        }
        return new Funcionario(funcionarioDTOV1.getId(), funcionarioDTOV1.getNome(), funcionarioDTOV1.getEmail(), funcionarioDTOV1.getCelular(), funcionarioDTOV1.getIdade());
    }


    public static UserDTOV1 toDTOV1(UserV1 userV1){
        if(Objects.isNull(userV1)){
            return null;
        }
        return new UserDTOV1()
                .builder()
                .id(funcionario.getId())
                .nome(funcionario.getNome())
                .email(funcionario.getEmail())
                .idade(funcionario.getIdade())
                .celular(funcionario.getCelular())
                .build();
    }

}
