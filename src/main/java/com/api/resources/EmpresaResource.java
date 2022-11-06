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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.api.domain.CredencialMercadoLivre;
import com.api.domain.CredencialMercadoPago;
import com.api.domain.Estabelecimento;
import com.api.repository.EmpresaRepository;
import com.api.services.EmpresaService;

//@autor Jadson Feitosa #29

@RestController
@RequestMapping("/api/empresa")
public class EmpresaResource implements ResourceBase<Estabelecimento, Long>{
	
	@Autowired
	private EmpresaService empresaService;
	
	@Autowired
	private EmpresaRepository empresaRepository;

//	Salvar Empresa
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Estabelecimento> save(@Valid @RequestBody Estabelecimento pEntity, HttpServletResponse response) {
		Estabelecimento EmpresaSalvo = null;
		EmpresaSalvo = empresaService.save(pEntity);
		return ResponseEntity.status(HttpStatus.CREATED).body(EmpresaSalvo);
	}
	
//	Salvar Credenciasi mercado livre
	@PostMapping("/cred-livre")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<String> save(@Valid  @RequestBody CredencialMercadoLivre pEntity, HttpServletResponse response){ 
		empresaService.saveCredencialMercadoLivre(pEntity);
		return ResponseEntity.ok("Credenciais salvas");
	}
	
//	Salvar Credenciasi mercado livre
	@PostMapping("/cred-pago")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<String> save(@Valid @RequestBody CredencialMercadoPago pEntity, HttpServletResponse response){ 
		empresaService.saveCredencialMercadoPago(pEntity);
		return ResponseEntity.ok("Credenciais salvas");
	}

//	Atualizar entidade 
	@PutMapping("/{pID}")
	public ResponseEntity<Estabelecimento> update(@Valid @PathVariable Long pID, @RequestBody Estabelecimento pEntity) {
		Estabelecimento EmpresaSalvo = empresaService.update(pID, pEntity);
		return ResponseEntity.ok(EmpresaSalvo);
	}

//	Deletar Empresa
	@DeleteMapping("/{pID}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long pID) {
		empresaRepository.deleteById(pID);
	}
	
//	Filtro por ID
	@GetMapping("/{pID}")
	@Override
	public ResponseEntity<Estabelecimento> findById(Long pID) {
		// TODO Auto-generated method stub
		return ResponseEntity.ok(empresaRepository.findById(pID).get());
	}


	@GetMapping("/get-livre/{pID}")
	public ResponseEntity<CredencialMercadoLivre> findByIdCredencialLivre(@PathVariable Long pID) {
		return ResponseEntity.ok(empresaService.findCredencialMercadoLivre(pID));
	}
	
	@GetMapping("/get-pago/{pID}")
	public ResponseEntity<CredencialMercadoPago> findByIdCredencialPago(@PathVariable Long pID) {
		return ResponseEntity.ok(empresaService.findCredencialMercadoPago(pID));
	}
	
	@PostMapping("/deleteall")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteAll( @RequestBody List<Estabelecimento> pList) {
		empresaRepository.deleteAll(pList);
	}

	@GetMapping
	public List<Estabelecimento> findAllList() {
		return empresaRepository.findAll();
	}

	@Override
	public Page<Estabelecimento> findAllPage(Estabelecimento pFilter, Pageable pPage) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
