package br.com.central.erros.impl.business.entity.enums;

public enum Level {

    ERROR(0, "Error"),
    WARNING(1, "Warning"),
    DEBUG(2, "Debug");

    private int cod;
    private String descricao;

    private Level(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public int getCod() {
        return cod;
    }

    public String getDescricao () {
        return descricao;
    }

    public static Level toEnum(Integer cod) {

        if (cod == null) {
            return null;
        }

        for (Level x : Level.values()) {
            if (cod.equals(x.getCod())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Id inválido: " + cod);
    }
}
