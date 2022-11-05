package com.api.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Luan Miranda
 * @Demanda AE-67
 */

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CredencialMercadoLivre {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String client_id;
	
	@NotBlank
	private String client_secret;
	

	private String access_token;
	

	private String token_type;
	

	private Integer expires_in;
	

	private String scope;
	

	private Integer user_id;
	

	private String refresh_token;
	
	@NotBlank
	private String status;
	

}
