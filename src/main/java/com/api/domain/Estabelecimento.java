package com.api.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.api.domain.enuns.Regime;
import com.api.domain.enuns.UF;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * @author Luan Miranda
 * @Demanda AE-67
 */

@Entity
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Estabelecimento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Size( min=14, max = 14)
	private String cnpj;
	
	@Size( min=11, max = 11)
	private String cpf;
	
	private String instEstadual;
	
	private String instMunicipal;
	
	@NotNull
	private String razao;
	
	
	private String nome;
	
	
    private String logradouro;
	
	
	private String numero;
	
	
	private String bairro;
	
	
	private String cidade;
	
	
	private String cep;

	@Enumerated(EnumType.STRING)
	private UF uf;
	
	private String logo;
	
	private String codIbge;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private Regime regime;
//	contatos--------------------------------------
	
	@NotNull
	private String email;
	
	
	private String email2;
	
	@NotNull
	private String celular1;
	
	
	private String celular2;
	
	
	private String foneFixo;
	
//	se for igual a zero é porque tem filiais se for null é porque não existe filial
	private String matrizId;
	
	private String status;
	
	
	
	
	
	
	
	
	
	

}
