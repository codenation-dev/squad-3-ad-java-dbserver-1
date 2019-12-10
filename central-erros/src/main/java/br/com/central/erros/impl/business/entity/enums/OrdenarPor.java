package br.com.central.erros.impl.business.entity.enums;

import br.com.central.erros.impl.business.entity.V1.LogV1;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public enum OrdenarPor {

    LEVEL {
        @Override
        public List<LogV1> metodoOrdenarPor(List<LogV1> listaInput) {
            List<LogV1> lista = listaInput.stream().sorted(Comparator.comparing(LogV1::getLevel)).collect(Collectors.toList());
            return lista;
        }
    },

    FREQUENCIA {
        @Override
        public List<LogV1> metodoOrdenarPor(List<LogV1> listaInput) {

            List<LogV1> lista = listaInput.stream().sorted(Comparator.comparing(LogV1::getNumeroDeEventos).reversed()).collect(Collectors.toList());
            return lista;
        }

    };

    public abstract List<LogV1> metodoOrdenarPor(List<LogV1> listaInput);

}

