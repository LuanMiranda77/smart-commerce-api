package com.api.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.api.domain.Categoria;
import com.api.domain.Produto;
import com.api.domain.enuns.EstatusPedido;

//@autor Jadson Feitosa #29

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	
	public Produto findByCodigoBarras(String codigoBarras);
	
	public boolean existsByCodigoBarras(String codigoBarras);
	
	public List<Produto> findProdutoByTituloContains(String title);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("from Produto produto where produto.categoria.id =:id")
	public List<Produto> findProdutoByCategoria(@Param("id") Long id);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("Update Produto produto SET produto.idMarketplace =:code where produto.id =:id")
	public void updateIDMarket(@Param("id") Long id, @Param("code")String code );

}
