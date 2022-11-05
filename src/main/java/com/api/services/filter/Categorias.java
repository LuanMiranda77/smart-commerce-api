package com.api.services.filter;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Categorias {
	   
	private String nome;
	private Long valor;
	
	
	public Categorias(String nome, Long valor) {
		super();
		this.nome = nome;
		this.valor = valor;
	}


	public Categorias() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	   
}//CATEGORIA
