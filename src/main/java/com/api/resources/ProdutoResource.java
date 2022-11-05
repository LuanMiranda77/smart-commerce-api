package com.api.resources;

import java.awt.print.Pageable;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.api.domain.ItemMarkeplace;
import com.api.domain.Pedido;
import com.api.domain.Produto;
import com.api.domain.enuns.EstatusPedido;
import com.api.repository.ItemMarketRepository;
import com.api.repository.ProdutoRepository;
import com.api.services.ProdutoService;
import com.fasterxml.jackson.databind.ObjectMapper;

//@autor Jadson Feitosa #29

@RestController
@RequestMapping("/api/produto")
public class ProdutoResource implements ResourceBase<Produto, Long>{
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private ItemMarketRepository itemMarkeplaceRepository;


//	Salvar Produto
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Produto> save(@Valid @RequestBody Produto pEntity, HttpServletResponse response) {
		Produto produtoSalvo = null;
		try {
			produtoSalvo = produtoService.save(pEntity);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoSalvo);
	}
	
//	Salvar item Markewt
	@PostMapping("/item-market")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<ItemMarkeplace> saveItemMarket(@Valid @RequestBody ItemMarkeplace pEntity, HttpServletResponse response) {
		ItemMarkeplace produtoSalvo = null;
		try {
			produtoRepository.updateIDMarket(pEntity.getProduto().getId(), pEntity.getIdMarkeplace());
			produtoSalvo = itemMarkeplaceRepository.save(pEntity);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoSalvo);
	}

//	Atualizar entidade 
	@PutMapping("/{pID}")
	public ResponseEntity<Produto> update(@Valid @PathVariable Long pID, @RequestBody Produto pEntity) {
		Produto produtoSalvo = produtoService.update(pID, pEntity);
		return ResponseEntity.ok(produtoSalvo);
	}

//	Deletar produto
	@DeleteMapping("/{pID}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long pID) {
		produtoRepository.deleteById(pID);
	}

//	Filtro por ID
	@GetMapping("/{pID}")
	public ResponseEntity<Produto> findById(@PathVariable Long pID) {
		return ResponseEntity.ok(produtoRepository.findById(pID).get());
	}
	
//	Filtro por ID
	@GetMapping("/busca/{ptitulo}")
	public ResponseEntity<List<Produto>> findByTitulo(@PathVariable String ptitulo) {
		List<Produto> lista = produtoRepository.findProdutoByTituloContains(ptitulo);
		
		return ResponseEntity.ok(lista);
	}
	
	@PostMapping("/deleteall")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteAll( @RequestBody List<Produto> pList) {
		produtoRepository.deleteAll(pList);
	}
	
//	Atualizar pedido
	@PutMapping("/update-market/{id}/{code}")
	public ResponseEntity<Pedido> updateStatus(@PathVariable Long id, @PathVariable String code) {
		produtoRepository.updateIDMarket(id, code);
		return ResponseEntity.ok(null);
	}

	
	@GetMapping("/filter/{tipoFilter}&{dados}")
	public List<Produto> findFilterProdutos(@PathVariable String tipoFilter, @PathVariable String dados){
		return produtoService.findFilterProdutos(tipoFilter, dados);
	}
	
	@GetMapping("/categoria/{id}")
	public List<Produto> findProdutosByCategoria(@PathVariable Long id){
		return produtoRepository.findProdutoByCategoria(id);
	}
	
	public Page<Produto> findAllPage(Produto pFilter, Pageable pPage) {
		return null;
	}

	@GetMapping
	public List<Produto> findAllList() {
		return produtoRepository.findAll();
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/send")
    public ResponseEntity<String> receiveData(String pessoaJson, MultipartFile foto) {

        ObjectMapper mapper = new ObjectMapper();
        Produto pessoa = null;

        try {
            pessoa = mapper.readValue(pessoaJson, Produto.class);
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Não foi possível ler o json");
        }

        System.out.println(pessoa);
        System.out.println(foto.getOriginalFilename());
        return ResponseEntity.ok("Deu certo!");
    }

}
