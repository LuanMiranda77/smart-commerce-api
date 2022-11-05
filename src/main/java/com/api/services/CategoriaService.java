package com.api.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.domain.Categoria;
import com.api.repository.CategoriaRepository;
import com.api.services.exceptions.ItemExistException;

//@autor Jadson Feitosa #AE-39

@Service
public class CategoriaService {
	

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Categoria save(Categoria pEntity) {
		
		testeExistCategoria(pEntity);
		
		return categoriaRepository.save(pEntity);
		
	}
	
	public Categoria update( Categoria pEntity) {
		Categoria categoriaSalvo = categoriaRepository.findById(pEntity.getId()).get();
		
		BeanUtils.copyProperties(pEntity, categoriaSalvo,"id");
		categoriaRepository.save(categoriaSalvo);
		categoriaSalvo.setId(pEntity.getId());
		
		return categoriaSalvo;
	}
	
	public void testeExistCategoria(Categoria pEntity) {
		if(categoriaRepository.existsByNome(pEntity.getNome())) {
			throw new ItemExistException();
		}
	}
	
}
