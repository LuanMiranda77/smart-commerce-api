package com.api.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.domain.ConfigModulo;

@Repository
public interface ConfigModuloRepository extends JpaRepository<ConfigModulo, Long> {
	
//	public ConfigModulo findByEstabelecimento(Long estabelecimento);

}
