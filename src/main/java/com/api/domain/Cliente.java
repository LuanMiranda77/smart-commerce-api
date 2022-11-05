package com.api.domain;

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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.api.domain.enuns.Sexo;
import com.api.domain.enuns.TipoCliente;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

//@autor Jadson Feitosa #40

@Entity
@Data
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	
	@NotBlank
	@Size(min=11)
	private String cpfCnpj;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private TipoCliente tipo;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "cliente_id")
	private List<Endereco> enderecos = new ArrayList<>();
	
	private String telefone;
	
	private String celular;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private Sexo sexo;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;

}
