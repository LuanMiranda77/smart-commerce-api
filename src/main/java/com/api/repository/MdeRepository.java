package com.api.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.domain.Mde;

@Repository
public interface MdeRepository extends JpaRepository<Mde, Long>{
	
	public boolean existsByCnpjCpf(String cnpjCpf);

}
