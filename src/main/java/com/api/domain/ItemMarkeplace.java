package com.api.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.api.domain.enuns.TipoMarkeplace;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
public class ItemMarkeplace {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	@JoinColumn(name ="produto_id")
	private Produto produto;
	
	private String idMarkeplace;
	
	@Enumerated(EnumType.ORDINAL)
	private TipoMarkeplace tipo;
	

}
