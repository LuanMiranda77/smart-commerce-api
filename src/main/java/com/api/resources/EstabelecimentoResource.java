package com.api.resources;

import java.awt.print.Pageable;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.api.domain.Estabelecimento;
import com.api.domain.enuns.TipoUsuario;
import com.api.repository.EstabelecimentoRepository;
import com.api.services.EstabelecimentoService;

@RestController
@RequestMapping("/api/estabelecimento")
public class EstabelecimentoResource implements ResourceBase<Estabelecimento, Long> {
	
	@Autowired
	private EstabelecimentoRepository estabelecimentoRepository;
	
	@Autowired
	private EstabelecimentoService estabelecimentoService;

	//	Salvar estabelecimento
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Estabelecimento> save(@Valid @RequestBody Estabelecimento pEntity, HttpServletResponse response) {
		Estabelecimento EmpresaSalvo = null;
		EmpresaSalvo = estabelecimentoService.save(pEntity);
		return ResponseEntity.status(HttpStatus.CREATED).body(EmpresaSalvo);
	}

	@Override
	public ResponseEntity<Estabelecimento> update(@Valid Long pID, Estabelecimento pEntity) {
		// TODO Auto-generated method stub
		return null;
	}

	@DeleteMapping("/{pID}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(Long pID) {
		// TODO Auto-generated method stub
		
	}

	public ResponseEntity<Estabelecimento> findById(Long pID) {
		// TODO Auto-generated method stub
		return ResponseEntity.ok(estabelecimentoRepository.findById(pID).get());
	}

	@Override
	public Page<Estabelecimento> findAllPage(Estabelecimento pFilter, Pageable pPage) {
		// TODO Auto-generated method stub
		return null;
	}

	@GetMapping("/estabelecimentos/{estabelecimento}/{tipo}")
	public List<Estabelecimento> findAllList(@PathVariable Long estabelecimento, @PathVariable String tipo) {
		if(tipo.equals(TipoUsuario.MASTER.getDescricao())) {
			return estabelecimentoRepository.findAll();
		}else {
			List<Estabelecimento> lista = new ArrayList<>();
			Estabelecimento estabelecimentoSalvo  = estabelecimentoRepository.getById(estabelecimento);
			if(estabelecimentoSalvo.getMatrizId() !=null && estabelecimentoSalvo.getMatrizId().equals("0") && (tipo.equals(TipoUsuario.ADMIN.getDescricao()) || tipo.equals(TipoUsuario.GERENTE.getDescricao()))) {
				lista = estabelecimentoRepository.findEstabelecimentoByFiliais(""+estabelecimento);
			}else {
				lista.add(estabelecimentoSalvo);
			}
			return lista;
		}
	}

	@GetMapping
	public List<Estabelecimento> findAllList() {
		return estabelecimentoRepository.findAll();
	}
	
	@PutMapping("/status/{id}/{status}")
	public ResponseEntity<Estabelecimento> setStatus(@PathVariable Long id, @PathVariable String status) {
		estabelecimentoService.updateStatus(id, status);
		return ResponseEntity.ok(null);
	}

}
