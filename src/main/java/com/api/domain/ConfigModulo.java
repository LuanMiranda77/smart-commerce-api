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
	private String orcamento="S";
	@Size(max = 1)
	private String pedido="S";
	@Size(max = 1)
	private String filial="N";
	@Size(max = 1)
	private String prevenda="N";

    // modo fiscal
	private String nfe="S";
	@Size(max = 1)
	private String nfce="S";
	@Size(max = 1)
	private String mdfe="S";
	@Size(max = 1)
	private String sat="N";


    // modo financeiro
	@Size(max = 1)
	private String contas="S";
	@Size(max = 1)
	private String planoContas="N";
	@Size(max = 1)
	private String controleCaixa="N";

    // modo estoque
	@Size(max = 1)
	private String transferenciaEstoque="N";
	
	private Integer numCasaDecimais=1;

    // integrações
	@Size(max = 1)
	private String balanca="N";
    
}
