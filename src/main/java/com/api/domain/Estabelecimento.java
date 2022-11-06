package com.api.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.api.domain.enuns.UF;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

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
	
	@NotBlank
	@Size( min=18, max = 18)
	private String cnpj;
	
	private String instEstadual;
	
	private String instMunicipal;
	
	
	private String razaoSocial;
	
	
	private String nomeFantasia;
	
	
    private String logradouro;
	
	
	private String numero;
	
	
	private String complemento;
	
	
	private String bairro;
	
	
	private String cidade;
	
	
	private String cep;

	@Enumerated(EnumType.STRING)
	private UF uf;
	
//	contatos--------------------------------------
	
	
	private String emailPrincipal;
	
	
	private String emailSegundario;
	
	
	private String celular1;
	
	
	private String celular2;
	
	
	private String foneFixo;
	
	
	
	
	
	
	
	
	
	

}
