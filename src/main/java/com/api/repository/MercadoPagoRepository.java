package com.api.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.domain.CredencialMercadoPago;

/**
 * 
 * @author Luan Miranda
 * @Demanada AE-67
 */

@Repository
public interface MercadoPagoRepository extends JpaRepository<CredencialMercadoPago, Long>{

}
