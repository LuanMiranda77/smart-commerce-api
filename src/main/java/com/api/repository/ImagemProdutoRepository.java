package com.api.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.domain.ImagemProduto;


@Repository
public interface ImagemProdutoRepository extends JpaRepository<ImagemProduto, Long>{

}
