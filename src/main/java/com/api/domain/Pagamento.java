package com.api.domain;



import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.api.domain.enuns.EstatusPagamento;
import com.api.domain.enuns.TipoPagamento;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

//@autor Jadson Feitosa #AE-36

@Entity
@Data
public class Pagamento implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Integer numeroDeParcelas;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataVencimento;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataPagamento;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private TipoPagamento tipo;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private EstatusPagamento estatus;
	
	
	
	
}
 