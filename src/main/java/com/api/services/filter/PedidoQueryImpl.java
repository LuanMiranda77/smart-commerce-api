package com.api.services.filter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Service;

import com.api.domain.Cliente;
import com.api.domain.ItemPedido;
import com.api.domain.Pedido;
import com.api.domain.enuns.EstatusPedido;

@Service
public class PedidoQueryImpl {

	@PersistenceContext
	private EntityManager manager;

	public List<Pedido> findPedidosByEstatus(Date dtIni, Date dtFin, EstatusPedido estatusPedido) {

		List<Pedido> pedidos = new ArrayList<>();
		CriteriaBuilder builder = manager.getCriteriaBuilder();

		CriteriaQuery<Pedido> query = builder.createQuery(Pedido.class);
		Root<Pedido> root = query.from(Pedido.class);

		query.select(root);
		query.where(builder.between(root.get("dataFechamento"), dtIni, dtFin));
		query.where(builder.equal(root.get("estatus"), estatusPedido));

		pedidos = manager.createQuery(query).getResultList();
		
		return pedidos;

	}

	public List<Pedido> findPedidosByCliente(Date dtIni, Date dtFin, Cliente cliente) {

		List<Pedido> pedidos = new ArrayList<>();
		CriteriaBuilder builder = manager.getCriteriaBuilder();

		CriteriaQuery<Pedido> query = builder.createQuery(Pedido.class);
		Root<Pedido> root = query.from(Pedido.class);

		query.select(root);
		query.where(builder.between(root.get("dataFechamento"), dtIni, dtFin));
		query.where(builder.equal(root.get("cliente"), cliente));

		pedidos = manager.createQuery(query).getResultList();
		
		pedidos.forEach(e -> { 
			
			CriteriaQuery<ItemPedido> query1 = builder.createQuery(ItemPedido.class);
			Root<ItemPedido> root2 = query1.from(ItemPedido.class);
			query1.select(root2);
			query1.where(builder.equal(root2.get("pedido"), e));
			List<ItemPedido> itens = manager.createQuery(query1).getResultList();
		
			e.setProdutos(itens);
		});

		return pedidos;

	}

	public List<Pedido> findPedidosByClienteStatus(Date dtIni, Date dtFin, Cliente cliente, EstatusPedido status) {

		List<Pedido> pedidos = new ArrayList<>();
		CriteriaBuilder builder = manager.getCriteriaBuilder();

		CriteriaQuery<Pedido> query = builder.createQuery(Pedido.class);
		Root<Pedido> root = query.from(Pedido.class);

		query.select(root);
		query.where(builder.between(root.get("dataFechamento"), dtIni, dtFin));
		query.where(builder.equal(root.get("cliente"), cliente));
		query.where(builder.equal(root.get("estatus"), status));

		pedidos = manager.createQuery(query).getResultList();

		return pedidos;

	}

}
