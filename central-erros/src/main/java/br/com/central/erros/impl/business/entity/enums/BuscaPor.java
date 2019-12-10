package br.com.central.erros.impl.business.entity.enums;

import br.com.central.erros.impl.business.entity.V1.LogV1;


import java.util.List;
import java.util.stream.Collectors;

public enum BuscaPor {

    DESCRICAO {
        @Override
        public List<LogV1> metodoBuscarPor(List<LogV1> listaInput, String detalhes) {
            List<LogV1> lista = listaInput.stream().filter(logV1 -> logV1.getDetalhes().toLowerCase().contains(detalhes)).collect(Collectors.toList());
            return lista;
        }
    },

        LEVEL {
            @Override
            public List<LogV1> metodoBuscarPor(List<LogV1> listaInput, String detalhes) {

                List<LogV1> lista = listaInput.stream().filter(logV1 -> logV1.getLevel().equals(Level.valueOf(detalhes))).collect(Collectors.toList());
                return lista;
            }

        },

    ORIGEM {

        @Override
        public List<LogV1> metodoBuscarPor(List<LogV1> listaInput, String detalhes) {
            List<LogV1> lista = listaInput.stream().filter(logV1 -> logV1.getIp().equals(detalhes)).collect(Collectors.toList());
            return lista;
        }
    };

    public abstract List<LogV1> metodoBuscarPor(List<LogV1> listaInput, String detalhes);

    }





