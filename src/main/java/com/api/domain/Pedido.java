package com.api.domain;



import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.api.domain.enuns.EstatusPedido;
import com.api.utils.UtilsHorasData;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

//@autor Jadson Feitosa #AE-36

@Entity
@Data
public class Pedido implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataDeCriacao;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataFechamento;
	
	
	@NotNull
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "pagamento_id")
	private Pagamento pagamento;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="cliente_id")
	private Cliente cliente;
	
	@NotNull
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "endereco_id")
	private EnderecoEntrega enderecoEntrega;
	
	@NotNull
//	@JsonIgnore
	@OneToMany(mappedBy = "produto")
	private List<ItemPedido> produtos = new ArrayList<ItemPedido>();
	
	private BigDecimal valorTotal = new BigDecimal(0);
	
	private BigDecimal valorDesconto = new BigDecimal(0);
	
	private BigDecimal valorFrete = new BigDecimal(0);
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private EstatusPedido estatus;
	
	private String codigoRastreio;
	
	@PrePersist
	public void setDataCriacao() {
		this.dataDeCriacao = UtilsHorasData.subtrair(new Date(), 3);
	}
	
}
