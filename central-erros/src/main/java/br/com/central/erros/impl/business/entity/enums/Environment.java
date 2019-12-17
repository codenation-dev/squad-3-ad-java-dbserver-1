package br.com.central.erros.impl.business.entity.enums;

public enum Environment {

    HOMOLOGATION(0, "Homologation"),
    PRODUCTION(1, "Production"),
    DEVELOPMENT(2, "Development");

    private final int cod;
    private final String descricao;

    Environment(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    private int getCod() {
        return cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Environment toEnum(Integer cod) {

        if (cod == null) {
            return null;
        }

        for (Environment x : Environment.values()) {
            if (cod.equals(x.getCod())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Id inválido: " + cod);
    }
}
