package com.api.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.domain.Categoria;

//@autor Jadson Feitosa #AE-39

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
	
	public boolean existsByNome(String nome);
	

}
