package com.api.domain;



import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

//@autor Jadson Feitosa #29

@Entity
@Data
public class Produto {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private Long codigo;
	
	@NotBlank
	private String ean;
	
	@NotNull
	@ManyToOne
    private Estabelecimento estabelecimento;
	
	@NotBlank
	private String nome;

	@NotNull
	private Float saldo;
	
    @JsonFormat(pattern="yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
    private Date dtCreate = new Date();
    
    @JsonFormat(pattern="yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date dtUpdate = new Date();
    
    @JsonFormat(pattern="yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date dtVencimento;
    
	private String nomeForn;
    
    @JsonFormat(pattern="yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date dtUltimaCompra;
    
    
    @Size(max=3)
    private String unid="UN";
    
    private Float fatorConversao=1.0F;
    
	private Float saldoMinimo=1.0F;
	
	private Float quantMinAtacado;
	
	@NotNull
	private BigDecimal precoCusto;
	
	@NotNull
	private BigDecimal precoVenda;
	
    @Column(name = "image", columnDefinition = "text")
	private String image;
    
	@NotNull
	private BigDecimal precoAtacado;
	
	@Size(max=1)
	private String status="S"; //paused ou active
	
	// impostos
	private String cfop;
	private String ncm;
	private String cest;

	private String cstIcms;
	private Float porcIcms;
	private Float valorIcms;

	private String cstIpi;
	private Float porcIpi;
	private Float valorIpi;

	private String cstPis;
	private Float porcPis;
	private Float valorPis;

	private String cstCofins;
	private Float porcConfis;
	private Float valorCofins;

//	@OneToMany(cascade = CascadeType.ALL)
//	@JoinColumn(name="produto_id")
//	private List<ImagemProduto> imagens = new ArrayList<>();
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="categoria_id")
	private Categoria categoria;
	
	@JsonIgnore
	@OneToMany(mappedBy = "pedido")
	private List<ItemPedido> pedidos = new ArrayList<ItemPedido>();
	

}
