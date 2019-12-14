package br.com.central.erros.impl.business.entity.enums;

import br.com.central.erros.impl.business.entity.V1.LogV1;

import java.util.List;
import java.util.stream.Collectors;

public enum FindBy {

    DESCRIPTION {
        @Override
        public List<LogV1> methodFindBy(List<LogV1> listInput, String details) {
            List<LogV1> lista = listInput.stream().filter(logV1 -> logV1.getDetails().toLowerCase().contains(details)).collect(Collectors.toList());
            return lista;
        }
    },

    LEVEL {
        @Override
        public List<LogV1> methodFindBy(List<LogV1> listInput, String details) {

            List<LogV1> lista = listInput.stream().filter(logV1 -> logV1.getLevel().equals(Level.valueOf(details))).collect(Collectors.toList());
            return lista;
        }

    },

    ORIGIN{

        @Override
        public List<LogV1> methodFindBy(List<LogV1> listInput, String details) {
            List<LogV1> lista = listInput.stream().filter(logV1 -> logV1.getIp().equals(details)).collect(Collectors.toList());
            return lista;
        }
    };

    public abstract List<LogV1> methodFindBy(List<LogV1> listInput, String details);

}

