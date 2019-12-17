package br.com.central.erros.impl.business.entity.enums;

public enum UserType {

	PESSOAFISICA(0, "Pessoa Física"),
	PESSOAJURIDICA(1, "Pessoa Jurídica");

	private final int code;
	private final String description;

	UserType(int code, String description) {
		this.code = code;
		this.description = description;
	}

	public int getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	public static UserType toEnum(Integer cod) {

		if (cod == null) {
			return null;
		}

		for (UserType x : UserType.values()) {
			if (cod.equals(x.getCode())) {
				return x;
			}
		}

		throw new IllegalArgumentException("Id inválido: " + cod);
	}

}
