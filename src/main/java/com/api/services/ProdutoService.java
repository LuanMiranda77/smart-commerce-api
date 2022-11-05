package com.api.services;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.api.domain.ImagemProduto;
import com.api.domain.Produto;
import com.api.repository.ImagemProdutoRepository;
import com.api.repository.ProdutoRepository;
import com.api.services.exceptions.ItemExistException;
import com.api.services.filter.ProdutoQueryImpl;

// @autor Jadson Feitosa #29

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private ImagemProdutoRepository imagemProdutoRepository;
	
	@Autowired
	private ProdutoQueryImpl produtoQueryImpl;

	public Produto save(Produto pEntity){
		if(produtoRepository.existsByCodigoBarras(pEntity.getCodigoBarras())) {
			throw new ItemExistException();
		}
		String [] vetor = pEntity.getDescricao().split("|");
		pEntity.setDescricao(vetor[0]);
		
		Produto produtoSalvo = produtoRepository.save(pEntity);
		System.err.println(pEntity.getDescricao());
		pEntity.getImagens().forEach(e -> System.err.println(e));
		return produtoSalvo;
	}

	public Produto update(Long pID, Produto pEntity) {
		Produto produtoSalvo = produtoRepository.findById(pID).get();
		
		List<ImagemProduto>ListaForDel = produtoSalvo.getImagens().stream()
					.filter(e -> pEntity.getImagens()
					.contains(e.getId())!=true).collect(Collectors.toList());
		
		BeanUtils.copyProperties(pEntity, produtoSalvo,"id");
		produtoRepository.save(produtoSalvo); 
		produtoSalvo.setId(pEntity.getId());
		imagemProdutoRepository.deleteAll(ListaForDel);
		return produtoSalvo;
	}
	
	public void deleteAll(List<Produto> pList) {
		produtoRepository.deleteAll(pList);
		
	}
	
	public List<Produto> findFilterProdutos(String tipoFilter, String dados){
		return produtoQueryImpl.findFilterProduto(tipoFilter, dados);
	}
	
		
}
