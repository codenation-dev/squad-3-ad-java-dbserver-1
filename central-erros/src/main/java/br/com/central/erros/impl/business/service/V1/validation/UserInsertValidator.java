package br.com.central.erros.impl.business.service.V1.validation;

import br.com.central.erros.impl.api.V1.exception.FieldMessage;
import br.com.central.erros.impl.business.dto.UserDTOV1;
import br.com.central.erros.impl.business.service.V1.UserServiceImplV1;
import br.com.central.erros.impl.business.service.V1.utils.BR;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class UserInsertValidator implements ConstraintValidator<UserInsert, UserDTOV1> {

    @Autowired
    private UserServiceImplV1 userService;

    @Override
    public void initialize(UserInsert ann) {
    }

    @Override
    public boolean isValid(UserDTOV1 objDto, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        if (objDto.getType().getCode() == 0 && !BR.isValidCPF(objDto.getCpfOrCnpj())) {
            list.add(new FieldMessage("cpfOrCnpj", "CPF inválido"));
        }

        if (objDto.getType().getCode() == 1 && !BR.isValidCNPJ(objDto.getCpfOrCnpj())) {
            list.add(new FieldMessage("cpfOrCnpj", "CNPJ inválido"));
        }

        Boolean aux = userService.existsByEmail(objDto.getEmail());

        if (aux == Boolean.TRUE) {
            list.add(new FieldMessage("email", "Email já existente"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}