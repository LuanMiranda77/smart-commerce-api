package com.api.services.filter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Service;

import com.api.domain.Pedido;
import com.api.domain.Produto;

@Service
public class ProdutoQueryImpl {
	
	@PersistenceContext
	private EntityManager manager;
	
	public List<Produto> findFilterProduto(String tipoFilter, String dados){
		
		List<Produto> produtos = new ArrayList<>();
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		
		CriteriaQuery<Produto> query = builder.createQuery(Produto.class);
		Root<Produto> root = query.from(Produto.class);
		
		if(tipoFilter.equals("ESTRELA")) {
			int estrelas = Integer.parseInt(dados);
			query.select(root);
			query.where(builder.between(root.get("estrelas"), estrelas-1, estrelas));
			
		}else if(tipoFilter.equals("DESCONTO")) {
			
		}else if(tipoFilter.equals("PRECO")) {
			String[] precos = dados.split("-");
			String tipoPreco= "precoVarejo";
			if(precos[2].equals("A")) {
				tipoPreco = "precoAtacado";
			}
			query.select(root);
			query.where(builder.between(root.get(tipoPreco), new BigDecimal(precos[0]), new BigDecimal(precos[1])));
		
		}else if(tipoFilter.equals("CATEGORIA")) {
			
			Predicate predicate = builder.and();
//			predicate = builder.and(predicate, builder.like(root.get("categorias").get("nome"), "%" + dados + "%"));
			query.where(root.join("categorias").in(dados));
			query.select(root).where(predicate).distinct(true);
			
		}
		
		produtos = manager.createQuery(query).getResultList();
		
		return produtos;
	}

}
