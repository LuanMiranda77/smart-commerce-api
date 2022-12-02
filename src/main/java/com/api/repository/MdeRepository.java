package com.api.repository;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.api.domain.Mde;

@Repository
public interface MdeRepository extends JpaRepository<Mde, Long>{
	
	public boolean existsByCnpjCpf(String cnpjCpf);
	
	@Query(value = "SELECT MAX(codigo) as codigo FROM mde  where estabelecimento_id=:estabelecimento", nativeQuery = true)
	public Long findMaxCodigoByEstabelecimento(Long estabelecimento);
	
	@Query(value = "SELECT * FROM mde  where estabelecimento_id=:estabelecimento and data_emissao between :dtIni and :dtFin", nativeQuery = true)
	public List<Mde> findNotaByEstabelecimentoAndEmisao(Long estabelecimento, Date dtIni, Date dtFin);
	
	@Query(value = "SELECT * FROM mde  where estabelecimento_id=:estabelecimento and data_manifesto between :dtIni and :dtFin", nativeQuery = true)
	public List<Mde> findNotaByEstabelecimentoAndManifesto(Long estabelecimento, Date dtIni, Date dtFin);
	
	@Query(value = "SELECT * FROM mde  where estabelecimento_id=:estabelecimento and data_entrada between :dtIni and :dtFin", nativeQuery = true)
	public List<Mde> findNotaByEstabelecimentoAndEntrada(Long estabelecimento, Date dtIni, Date dtFin);

}
