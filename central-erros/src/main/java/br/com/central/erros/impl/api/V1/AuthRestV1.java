package br.com.central.erros.impl.api.V1;

import br.com.central.erros.impl.business.dto.EmailDTO;
import br.com.central.erros.impl.business.service.V1.AuthService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/v1")
public class AuthRestV1 {
//
//    @Autowired
//    private AuthService service;
//
//    @PostMapping(value = "/recovery")
//    @ApiOperation(value = "Redefinir senha.")
//    public ResponseEntity<Void> recovery(@Valid @RequestBody EmailDTO objDto) {
//        service.sendNewPassword(objDto.getEmail());
//        return ResponseEntity.noContent().build();
//    }

}
