package br.com.central.erros.impl.business.entity.enums;

public enum Level {

    ERROR(0, "Error"),
    WARNING(1, "Warning"),
    DEBUG(2, "Debug");

    private final int cod;
    private final String description;

    Level(int cod, String description) {
        this.cod = cod;
        this.description = description;
    }

    private int getCod() {
        return cod;
    }

    public String getDescription() {
        return description;
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
