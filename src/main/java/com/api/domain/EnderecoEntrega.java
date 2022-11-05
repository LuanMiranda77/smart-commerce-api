package com.api.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.api.domain.enuns.UF;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;



@Entity
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class EnderecoEntrega  {
	
	//@autor Jadson Feitosa #43
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String logradouro;
		
	private String numero;
		
	private String complemento;
		
	private String bairro;
		
	private String cidade;
		
	private String cep;
	
	@Enumerated(EnumType.STRING)
	private UF uf;
	
}
