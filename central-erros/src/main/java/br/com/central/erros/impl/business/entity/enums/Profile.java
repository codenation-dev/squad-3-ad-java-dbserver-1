package br.com.central.erros.impl.business.entity.enums;

public enum Profile {


    ADMIN(0, "ROLE_ADMIN"),
    CLIENTE(1, "ROLE_CLIENTE");

    private final int cod;
    private final String descricao;

    Profile(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public int getCod() {
        return cod;
    }

    public String getDescricao () {
        return descricao;
    }

    public static Profile toEnum(Integer cod) {

        if (cod == null) {
            return null;
        }

        for (Profile x : Profile.values()) {
            if (cod.equals(x.getCod())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Id inválido: " + cod);
    }
}