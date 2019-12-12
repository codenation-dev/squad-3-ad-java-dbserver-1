package br.com.central.erros.impl.business.service.V1.validation;

import br.com.central.erros.impl.api.V1.exception.FieldMessage;
import br.com.central.erros.impl.business.dto.UserDTOV1;
import br.com.central.erros.impl.business.repository.V1.UserRepository;
import br.com.central.erros.impl.business.service.V1.utils.BR;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class UserInsertValidator implements ConstraintValidator<UserInsert, UserDTOV1> {

    @Autowired
    private UserRepository repo;

    @Override
    public void initialize(UserInsert ann) {
    }

    @Override
    public boolean isValid(UserDTOV1 objDto, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        if (objDto.getTipo().getCod() - 1 == 1 && !BR.isValidCPF(objDto.getCpfOuCnpj())) {
            list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
        }

        if (objDto.getTipo().getCod() - 1 == 2 && !BR.isValidCNPJ(objDto.getCpfOuCnpj())) {
            list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
        }

        Boolean aux = repo.existsByEmail(objDto.getEmail());

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