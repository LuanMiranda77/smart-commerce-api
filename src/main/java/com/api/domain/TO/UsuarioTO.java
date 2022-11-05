package com.api.domain.TO;

import java.io.Serializable;
import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.api.domain.Usuario;
import com.api.domain.enuns.Roles;

import lombok.Data;
@Data
public class UsuarioTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nome;
	private String login;
	private String email;
	private String password;
	private Date dataCriacao ;
	private Date dataAtualizacao;
	private Boolean status;
	private Roles role;
	
	public UsuarioTO() {
		super();
	}

	public UsuarioTO(Long id, String nome, String login, String email,String password, Date dataCriacao, Date dataAtualizacao,
			Boolean status, Roles role) {
		super();
		this.id = id;
		this.nome = nome;
		this.login = login;
		this.email = email;
		this.password = password;
		this.dataCriacao = dataCriacao;
		this.dataAtualizacao = dataAtualizacao;
		this.status = status;
		this.role = role;
	}
	
	
	public Usuario converteParaEntidade(UsuarioTO to) {
		Usuario usuario = new Usuario();
		BeanUtils.copyProperties(to, usuario,"password");
		return usuario;
	}

	public static UsuarioTO converteParaTO(Usuario usuario) {
		UsuarioTO to = new UsuarioTO();
		BeanUtils.copyProperties(usuario, to, "password");
		return to;
	}

	

}
