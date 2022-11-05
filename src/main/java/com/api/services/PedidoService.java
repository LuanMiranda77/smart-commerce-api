package com.api.services;



import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.domain.Cliente;
import com.api.domain.ItemPedido;
import com.api.domain.Pedido;
import com.api.domain.enuns.EstatusPagamento;
import com.api.domain.enuns.EstatusPedido;
import com.api.repository.ItemPedidoRepository;
import com.api.repository.PedidoRepository;
import com.api.services.exceptions.PagamentoNaoAprovadoException;
import com.api.services.filter.PedidoQueryImpl;

import net.bytebuddy.implementation.bytecode.Throw;

//@autor Jadson Feitosa #AE-36

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private PedidoQueryImpl pedidoQueryImpl;
	
	public Pedido save(Pedido pEntity) {
		List<ItemPedido>itemPedidos = pEntity.getProdutos();
		Pedido pedidoSalvo = pedidoRepository.save(pEntity);
		
		for (int i = 0; i < itemPedidos.size(); i++) {
			pEntity.getProdutos().get(i).setPedido(pedidoSalvo);
			pEntity.getProdutos().get(i).setDataVenda(pedidoSalvo.getDataFechamento());
			itemPedidoRepository.save(pEntity.getProdutos().get(i));
		}
		return pedidoRepository.save(pEntity);
	}
	
	public Pedido update(Pedido pEntity) {
		Pedido pedidoSalvo = null;
		
		if(pEntity.getEstatus().equals(EstatusPedido.FINALIZADO)) {
			if(pEntity.getPagamento().getEstatus().equals(EstatusPagamento.APROVADO)) {
				pedidoSalvo = pedidoRepository.findById(pEntity.getId()).get();
				BeanUtils.copyProperties(pEntity, pedidoSalvo, "id");
				pedidoRepository.save(pedidoSalvo);
			}
			else {
				throw new PagamentoNaoAprovadoException();
			}
		}else {
			
			pedidoSalvo = pedidoRepository.findById(pEntity.getId()).get();
			BeanUtils.copyProperties(pEntity, pedidoSalvo, "id");
			pedidoRepository.save(pedidoSalvo);
			pedidoSalvo.setId(pEntity.getId());
		}
		return pedidoSalvo;
	}
	
	public void isAtive(Pedido pEntity) {
		update(pEntity);
	}
	
	public  List<Pedido> findPedidosByEstatus(Date dtIni, Date dtFin, EstatusPedido estatusPedido){
		return pedidoQueryImpl.findPedidosByEstatus(dtIni, dtFin, estatusPedido);
	}
	
	public  List<Pedido> findPedidosByCliente(Date dtIni, Date dtFin, Cliente cliente){
		return pedidoQueryImpl.findPedidosByCliente(dtIni, dtFin, cliente);
	}
	
	public  List<Pedido> findPedidosByClienteStatus(Date dtIni, Date dtFin, Cliente cliente, EstatusPedido status){
		return pedidoQueryImpl.findPedidosByClienteStatus(dtIni, dtFin, cliente, status);
	}
		
}
