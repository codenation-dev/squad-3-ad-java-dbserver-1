package br.com.central.erros.impl.business.entity.enums;

import br.com.central.erros.impl.business.entity.V1.LogV1;

import javax.validation.constraints.Null;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BuscaPor {

    ;

    private String metodo;

    private BuscaPor(String metodo){
        this.metodo = metodo;
    }


    public List<LogV1> getDetalhes(List<LogV1> listaInput, String detalhes) {
        List<LogV1> lista = listaInput.stream().filter(logV1 -> logV1.getDetalhes().contains(detalhes)).collect(Collectors.toList());
        return lista;
    }


    //  List<LogV1> lista = listaInput.stream().filter(logV1 -> logV1.getLevel().getDescricao().equals(level)).collect(Collectors.toList());



    //  List<LogV1> lista = listaInput.stream().filter(logV1 -> logV1.getDetalhes().equals(descricao)).collect(Collectors.toList());

}
