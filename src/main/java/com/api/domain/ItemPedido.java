package com.api.domain;



import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.api.utils.UtilsHorasData;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

//@autor Jadson Feitosa #AE-36

@Entity
@Data

public class ItemPedido implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="pedido_id")
	private Pedido pedido;
	
	@ManyToOne
	@JoinColumn(name="produto_id")
	private Produto produto;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataVenda;

	private int quantidadeVendida;
	
	@PrePersist
	private void setDataVenda() {
		this.dataVenda = UtilsHorasData.subtrair(new Date(), 3);
	}
	


}
