package com.api.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.domain.ItemPedido;
import com.api.repository.ItemPedidoRepository;

//@autor Jadson Feitosa #AE-36

@Service
public class ItemPedidoService {
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public ItemPedido save(ItemPedido pEntity) {
		return itemPedidoRepository.save(pEntity);
	}
	
	public ItemPedido update(ItemPedido pEntity) {
		ItemPedido itemPedidoSalvo = itemPedidoRepository.findById(pEntity.getId()).get();
		
		BeanUtils.copyProperties(pEntity, itemPedidoSalvo, "id");
		itemPedidoRepository.save(itemPedidoSalvo);
		itemPedidoSalvo.setId(pEntity.getId());
		return itemPedidoSalvo;
		
	}
	
	public void isAtive(ItemPedido pEntity) {
		update(pEntity);
	}

}
