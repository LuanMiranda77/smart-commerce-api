package com.api.domain.enuns;

//@autor Jadson Feitosa #42

public enum TipoUsuario {
	
	MASTER("M"), CAIXA("C"), ESTOQUISTA("E"), ADMIN("A"), REVENDA("R"), GERENTE("G");
	
	private String descricao;

	TipoUsuario(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

}
