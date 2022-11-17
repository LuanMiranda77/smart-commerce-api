package com.api.domain;


import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.api.domain.enuns.StatusUsuario;
import com.api.utils.UtilsHorasData;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

//@autor Jadson Feitosa #AE-42	

@Entity
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long codigo;
	
	private String cpf;
	
	private String nome;
	
	@NotBlank
	@Email
	private String email;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCriacao;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataAtualizacao;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	private Date acesso;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private StatusUsuario status;
	
//	@JsonIgnore
	@NotBlank
	@Size(min = 6)
	private String password;
	
	private String celular;
	
	@Size(max=1)
	private String cargo;
	
	private String roles;
	
	
	@OneToOne
//	(cascade = CascadeType.ALL)
	private Estabelecimento estabelecimento;
	
	@JsonIgnore
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	private Date sincTemp;

	@PrePersist
	public void dataInicial() {
		this.dataCriacao = UtilsHorasData.subtrair(new Date(), 3);
		this.sincTemp = UtilsHorasData.subtrair(new Date(), 3);
	}
	
	@PreUpdate
	public void dataAtualizacao() {
		this.dataAtualizacao = UtilsHorasData.subtrair(new Date(), 3);
		this.sincTemp = UtilsHorasData.subtrair(new Date(), 3);
	}
	
	
}
