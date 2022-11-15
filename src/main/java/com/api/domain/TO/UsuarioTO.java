package com.api.domain.TO;

import java.io.Serializable;
import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.api.domain.Estabelecimento;
import com.api.domain.Usuario;
import com.api.domain.enuns.Roles;
import com.api.domain.enuns.StatusUsuario;

import lombok.Data;
@Data
public class UsuarioTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String cpf;
	private Long codigo;
	private String nome;
	private Date dataCriacao ;
	private Date dataAtualizacao;
	private Date acesso;
	private StatusUsuario status;
	private String celular;
	private String cargo;
	private String email;
	private String password;
	private String roles;
	private Long estabelecimento;
//	private Date sincTemp;
	
	public UsuarioTO() {
		super();
	}

	public UsuarioTO(Long id, String nome, String email,String password, Date dataCriacao, Date dataAtualizacao,
			StatusUsuario status, String roles) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.password = password;
		this.dataCriacao = dataCriacao;
		this.dataAtualizacao = dataAtualizacao;
		this.status = status;
		this.roles = roles;
		
	}
	
	
	public Usuario converteParaEntidade(UsuarioTO to) {
		Usuario usuario = new Usuario();
		BeanUtils.copyProperties(to, usuario,"password");
		return usuario;
	}
	
	public Usuario converteParaEntidadeSemEstabelecimento(UsuarioTO to) {
		Usuario usuario = new Usuario();
		BeanUtils.copyProperties(to, usuario, "estabelecimento");
		return usuario;
	}

	public static UsuarioTO converteParaTO(Usuario usuario) {
		UsuarioTO to = new UsuarioTO();
		BeanUtils.copyProperties(usuario, to, "password");
		return to;
	}

	

}
