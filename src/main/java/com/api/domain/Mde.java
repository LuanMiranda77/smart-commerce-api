package com.api.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Mde  implements Serializable  {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    private Long estabelecimento;
    
    private Long numNota;
    private int serie;
    private String chaveAcesso;
    
    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
    private Date dataEmissao;
    
    @JsonFormat(pattern="yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
    private Date dataManifesto;
    
    @JsonFormat(pattern="yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
    private Date dataEntrada ;
    
    private String cnpjCpf;
    private String fornecedor;
    
//    impostos
    private Float valorTotalNota; //vProd
    private Float valorDesc;
    private Float valorTotalNotaLiquido;
    private Float valorBaseIcms;
    private Float valorIcms;
    private Float valorBaseSubTributa;
    private Float valorSubTributa;
    private Float valorPis;
    private Float valorIpi;
    private Float valorCofins;
    private Float valorFrete;
    private Float valorOutros;
    
    private String numProtocolo;
    private String numNSU;
    
    @Size(max = 1)
    private String status; //M = manifesta  & N = a manifesta & A = nota avulsa
    @Size(max = 1)
    private String incluida = "N";

}
