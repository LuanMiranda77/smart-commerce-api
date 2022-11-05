package com.api.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.domain.Pagamento;
import com.api.repository.PagamentoRepository;

//@autor Jadson Feitosa #AE-36

@Service
public class PagamentoService {
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	public Pagamento save(Pagamento pEntity) {
		return pagamentoRepository.save(pEntity);
	}
	
	public Pagamento update(Pagamento pEntity) {
		Pagamento pagamentoSalvo = pagamentoRepository.findById(pEntity.getId()).get();
		
		BeanUtils.copyProperties(pEntity, pagamentoSalvo, "id");
		pagamentoRepository.save(pagamentoSalvo);
		pagamentoSalvo.setId(pEntity.getId());
		return pagamentoSalvo;
		
	}
	
	public void isAtive(Pagamento pEntity) {
		update(pEntity);
	}

}
