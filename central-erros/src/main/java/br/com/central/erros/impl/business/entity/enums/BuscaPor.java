package br.com.central.erros.impl.business.entity.enums;

import br.com.central.erros.impl.business.entity.V1.LogV1;

import java.util.List;
import java.util.stream.Collectors;

public class BuscaPor {

    ;

    private String metodo;

    private BuscaPor(String metodo){
        this.metodo = metodo;
    }


    public List<LogV1> getMetodo(List<LogV1> listaInput, Integer id) {
        List<LogV1> lista = listaInput.stream().filter(logV1 -> logV1.getUserV1().getId().equals(id)).collect(Collectors.toList());
        return lista;
    }


    //  List<LogV1> lista = listaInput.stream().filter(logV1 -> logV1.getLevel().getDescricao().equals(level)).collect(Collectors.toList());



    //  List<LogV1> lista = listaInput.stream().filter(logV1 -> logV1.getDetalhes().equals(descricao)).collect(Collectors.toList());

}
