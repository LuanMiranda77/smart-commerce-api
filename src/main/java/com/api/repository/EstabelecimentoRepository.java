package com.api.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.api.domain.Estabelecimento;
import com.api.domain.enuns.StatusUsuario;

@Repository
public interface EstabelecimentoRepository extends JpaRepository<Estabelecimento, Long>{
	
	@Query(value = "SELECT * FROM estabelecimento est where est.matriz_id=:id or est.matriz_id='0'", nativeQuery = true)
	public List<Estabelecimento> findEstabelecimentoByFiliais(@Param("id") String id);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("Update Estabelecimento est SET est.status =:status where est.id =:id")
	public void updateStatus(@Param("id") Long id, @Param("status") String status);
	
}
