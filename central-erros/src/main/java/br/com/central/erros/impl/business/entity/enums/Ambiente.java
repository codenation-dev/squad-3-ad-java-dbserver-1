package br.com.central.erros.impl.business.entity.enums;

public enum Ambiente {

    HOMOLOGATION(0, "Homologation"),
    PRODUCTION(1, "Production"),
    DEVELOPMENT(2, "Development");

    private int cod;
    private String descricao;

    private Ambiente(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public int getCod() {
        return cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Ambiente toEnum(Integer cod) {

        if (cod == null) {
            return null;
        }

        for (Ambiente x : Ambiente.values()) {
            if (cod.equals(x.getCod())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Id inválido: " + cod);
    }
}
