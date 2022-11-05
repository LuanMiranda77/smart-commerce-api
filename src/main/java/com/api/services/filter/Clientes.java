package com.api.services.filter;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Clientes {

	private String nome;
	private BigDecimal valor;
	
	
	
	public Clientes() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Clientes(String nome, BigDecimal valor) {
		super();
		this.nome = nome;
		this.valor = valor;
	}



}// CLIENTES
