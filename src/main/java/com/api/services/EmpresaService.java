package com.api.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.domain.CredencialMercadoLivre;
import com.api.domain.CredencialMercadoPago;
import com.api.domain.Estabelecimento;
import com.api.repository.EmpresaRepository;
import com.api.repository.MercadoLivreRepository;
import com.api.repository.MercadoPagoRepository;

@Service
public class EmpresaService implements ServiceBase<Estabelecimento, Long> {
	
	@Autowired
	EmpresaRepository repository;
	
	@Autowired
	MercadoLivreRepository livreRepository;
	
	@Autowired
	MercadoPagoRepository pagoRepository;
	
	@Override
	public Estabelecimento save(Estabelecimento pEntity) {
		Estabelecimento empresa = repository.save(pEntity); 
		return empresa;
	}

	@Override
	public Estabelecimento update(Long pID, Estabelecimento pEntity) {
		Estabelecimento empresa = repository.findById(pEntity.getId()).get();
		BeanUtils.copyProperties(pEntity, empresa, "id");
		this.save(empresa);
		empresa.setId(pEntity.getId());
		return empresa;
	}
	
	public void saveCredencialMercadoLivre(CredencialMercadoLivre pEntity) {
		livreRepository.save(pEntity); 
	}
	
	public void saveCredencialMercadoPago(CredencialMercadoPago pEntity) {
		pagoRepository.save(pEntity); 
	}
	
	public CredencialMercadoLivre findCredencialMercadoLivre(Long id) {
		return livreRepository.getById(id); 
	}
	
	public CredencialMercadoPago findCredencialMercadoPago(Long id) {
		return pagoRepository.getById(id); 
	}
	

}
