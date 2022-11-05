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

import com.api.domain.Pagamento;
import com.api.repository.PagamentoRepository;
import com.api.services.PagamentoService;

//@autor Jadson Feitosa #AE-36

@RestController
@RequestMapping("/api/pagamento")
public class PagamentoResource implements ResourceBase<Pagamento, Long>{

	@Autowired
	private PagamentoService pagamentoService;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
//	Salvar Pagamento
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Pagamento> save(@Valid Pagamento pEntity, HttpServletResponse response) {
		Pagamento pagamentoSalvo = pagamentoService.save(pEntity);
		return ResponseEntity.ok(pagamentoSalvo);
	}

//	Atualizar Pagamento
	@PutMapping
	public ResponseEntity<Pagamento> update(@Valid Long pID, Pagamento pEntity) {
		Pagamento pagamentoSalvo = pagamentoService.update(pEntity);
		return ResponseEntity.ok(pagamentoSalvo);
	}

//	Deletar pagamento	
	@DeleteMapping("/{pID}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(Long pID) {
		pagamentoRepository.deleteById(pID);
	}
	
//	Filtro por ID
	public ResponseEntity<Pagamento> findById(Long pID) {
		return ResponseEntity.ok(pagamentoRepository.findById(pID).get());
	}
	
//	Listar Pagamentos
	@GetMapping
	public List<Pagamento> findAllList() {
		return pagamentoRepository.findAll();
	}
	
	public Page<Pagamento> findAllPage(Pagamento pFilter, Pageable pPage) {
		return null;
	}
	

}
