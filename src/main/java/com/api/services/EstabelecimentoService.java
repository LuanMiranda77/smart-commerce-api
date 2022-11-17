package com.api.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.domain.CredencialMercadoLivre;
import com.api.domain.CredencialMercadoPago;
import com.api.domain.Estabelecimento;
import com.api.domain.enuns.StatusUsuario;
import com.api.repository.EmpresaRepository;
import com.api.repository.EstabelecimentoRepository;
import com.api.repository.MercadoLivreRepository;
import com.api.repository.MercadoPagoRepository;
import com.api.repository.UsuarioRepository;

@Service
public class EstabelecimentoService implements ServiceBase<Estabelecimento, Long> {
	
	@Autowired
	EstabelecimentoRepository repository;
	
	@Autowired
	UsuarioRepository userRepository;
	
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
	
	public void updateStatus(Long pID, String status) { 
		repository.updateStatus(pID, status);
		if(status.equals("S")) {
			userRepository.updateStatusByEstabelecimento(pID, "S");
		}else {
			userRepository.updateStatusByEstabelecimento(pID, "B");
		}
	}
	

}
