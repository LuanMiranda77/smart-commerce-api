package com.api.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.api.domain.Cliente;
import com.api.domain.Usuario;
import com.api.domain.enuns.TipoCliente;

//@autor Jadson Feitosa #AE-42

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	
	public Cliente findByUsuario(Usuario user);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("Update Cliente cliente SET cliente.tipo =:tipo where cliente.id =:id")
	public void updateStatus(@Param("id") Long id, @Param("tipo") TipoCliente tipo);

}
