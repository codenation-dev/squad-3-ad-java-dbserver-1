package br.com.central.erros.impl.business.entity.enums;

import br.com.central.erros.impl.business.entity.V1.LogV1;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public enum OrderBy {

    LEVEL {
        @Override
        public List<LogV1> methodOrderBy(List<LogV1> listInput) {
            List<LogV1> lista = listInput.stream().sorted(Comparator.comparing(LogV1::getLevel)).collect(Collectors.toList());
            return lista;
        }
    },

    FREQUENCY {
        @Override
        public List<LogV1> methodOrderBy(List<LogV1> listInput) {

            List<LogV1> lista = listInput.stream().sorted(Comparator.comparing(LogV1::getNumberOfEvents).reversed()).collect(Collectors.toList());
            return lista;
        }

    };

    public abstract List<LogV1> methodOrderBy(List<LogV1> listaInput);
}
