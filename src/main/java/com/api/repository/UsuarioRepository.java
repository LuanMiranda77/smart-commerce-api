package com.api.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.api.domain.Usuario;
import com.api.domain.enuns.EstatusUsuario;

//@autor Jadson Feitosa #AE-40

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	public Usuario findByEmail(String email);
	
	public boolean existsByEmail(String email);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("Update Usuario user SET user.status =:status where user.id =:id")
	public void updateStatus(@Param("id") Long id, @Param("status") EstatusUsuario estatus);
}
