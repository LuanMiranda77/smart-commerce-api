package com.api.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.domain.Cliente;
import com.api.domain.Estabelecimento;

//@autor Jadson Feitosa #AE-42

@Repository
public interface EmpresaRepository extends JpaRepository<Estabelecimento, Long>{

}
