package com.api.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.api.domain.Fornecedor;

//@autor Jadson Feitosa #AE-39

@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor, Long>{
	
	public boolean existsByCnpjCpf(String cnpjCpf);
	

}
