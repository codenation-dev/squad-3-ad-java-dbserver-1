package br.com.central.erros.impl.business.enums;

public enum Level {
    ERROR(1, "Error"), WARNING(2, "Warning"), DEBUG(3, "Debug");

    private Integer id;
    private String descricao;

    Level(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }
}
