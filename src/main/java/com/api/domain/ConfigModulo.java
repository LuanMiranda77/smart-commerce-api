package com.api.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ConfigModulo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	// modo geral
	@Size(max = 1)
	private String orcamento;
	@Size(max = 1)
	private String pedido;
	@Size(max = 1)
	private String filial;
	@Size(max = 1)
	private String prevenda;

    // modo fiscal
	private String nfe;
	@Size(max = 1)
	private String nfce;
	@Size(max = 1)
	private String mdfe;
	@Size(max = 1)
	private String sat;
	@Size(max = 1)
	private String mde;

    // modo financeiro
	@Size(max = 1)
	private String contas;
	@Size(max = 1)
	private String planoContas;
	@Size(max = 1)
	private String controleCaixa;

    // modo estoque
	@Size(max = 1)
	private String transferenciaEstoque;

    // integrações
	@Size(max = 1)
	private String balanca;
	
    Long estabelecimento;
    
	

}
