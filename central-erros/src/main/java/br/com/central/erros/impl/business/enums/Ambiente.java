package br.com.central.erros.impl.business.enums;

public enum Ambiente {
    HOMOLOGATION(1, "Homologation"), PRODUCTION(2, "Production"), DEVELOPMENT(3, "Development");
    private Integer id;
    private String descricao;
    Ambiente(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }
}
