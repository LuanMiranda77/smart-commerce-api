package com.api.domain;


import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
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

import com.api.utils.UtilsHorasData;
import com.fasterxml.jackson.annotation.JsonFormat;
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
	
	private String codigo;
	
	private String cpf;
	
	private String nome;
	
	@NotBlank
	@Email
	private String email;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCriacao = new Date();
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataAtualizacao = new Date();
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	private Date acesso = new Date();
	
	@NotNull
	@Size(max=1)
	private String status;
	
//	@JsonIgnore
	@NotBlank
	@Size(min = 6)
	private String password;
	
	private String celular;
	
	@Size(max=1)
	private String cargo;
	
	private String roles;
	
	@OneToOne
	private Estabelecimento estabelecimento;
	
	

	@PrePersist
	public void dataInicial() {
		this.dataCriacao = UtilsHorasData.subtrair(new Date(), 3);
	}
	
	@PreUpdate
	public void dataAtualizacao() {
		this.dataAtualizacao = UtilsHorasData.subtrair(new Date(), 3);
	}
	
	
}
