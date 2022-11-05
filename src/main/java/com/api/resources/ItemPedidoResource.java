package com.api.resources;

import java.awt.print.Pageable;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.api.domain.ItemPedido;
import com.api.repository.ItemPedidoRepository;
import com.api.services.ItemPedidoService;

//@autor Jadson Feitosa #AE-36

@RestController
@RequestMapping("/api/intemPedido")
public class ItemPedidoResource implements ResourceBase<ItemPedido, Long>{
	
	@Autowired
	private ItemPedidoService itemPedidoResource;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
//	Salvar itemPedido
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<ItemPedido> save(@Valid ItemPedido pEntity, HttpServletResponse response) {
		ItemPedido itemPedidoSalvo = itemPedidoResource.save(pEntity);
		return ResponseEntity.ok(itemPedidoSalvo);
	}

//	Atualizar itemPedido
	@PutMapping
	public ResponseEntity<ItemPedido> update(@Valid Long pID, ItemPedido pEntity) {
		ItemPedido itemPedidoSalvo = itemPedidoResource.update(pEntity);
		return ResponseEntity.ok(itemPedidoSalvo);
	}

//	Deletar itemPedido
	@DeleteMapping("/{pID}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(Long pID) {
		itemPedidoRepository.deleteById(pID);
	}

//	Filtro por ID
	public ResponseEntity<ItemPedido> findById(Long pID) {
		return  ResponseEntity.ok(itemPedidoRepository.findById(pID).get());
	}

//	Listar itemPedido
	@GetMapping
	public List<ItemPedido> findAllList() {
		return itemPedidoRepository.findAll();
	}

	public Page<ItemPedido> findAllPage(ItemPedido pFilter, Pageable pPage) {
		return null;
	}
	
}
