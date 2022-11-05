package com.api.domain;



import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

//@autor Jadson Feitosa #29

@Entity
@Data
public class Produto {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String codigoBarras;
	@NotBlank
	private String titulo;
	@NotNull
	private BigDecimal precoVarejo;
	@NotNull
	private BigDecimal precoAtacado;
	@NotNull
	private Integer quantidade;
	@NotBlank
	private String descricao;
	@NotBlank
	private String peso;
	@NotBlank
	private String comprimento;
	@NotBlank
	private String altura;
	@NotBlank
	private String largura;
	
	private Integer estrelas;
	
	private String idMarketplace;
	
	private String status="active"; //paused ou active

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="produto_id")
	private List<ImagemProduto> imagens = new ArrayList<>();
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="categoria_id")
	private Categoria categoria;
	
	@JsonIgnore
	@OneToMany(mappedBy = "pedido")
	private List<ItemPedido> pedidos = new ArrayList<ItemPedido>();
	

}
