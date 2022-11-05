package com.api.repository;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.api.domain.Pedido;
import com.api.domain.enuns.EstatusPedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>{
	
	public List<Pedido> findByDataDeCriacaoBetween(Date dtIni, Date dtFin );
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("Update Pedido pedido SET pedido.estatus =:estatus, pedido.codigoRastreio =:code  where pedido.id =:id")
	public void updateStatus(@Param("id") Long id, @Param("estatus") EstatusPedido estatus, @Param("code")String code );

}
