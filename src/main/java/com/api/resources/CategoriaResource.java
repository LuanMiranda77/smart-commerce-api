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

import com.api.domain.Categoria;
import com.api.repository.CategoriaRepository;
import com.api.services.CategoriaService;

//@autor Jadson Feitosa #AE-39

@RestController
@RequestMapping("/api/categoria")
public class CategoriaResource implements ResourceBase<Categoria,Long>{
	
	@Autowired
	private CategoriaService categoriaService;
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	
//	Salvar Categoria	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Categoria> save(@Valid @RequestBody Categoria pEntity, HttpServletResponse response) {
		Categoria categoriaSalva = categoriaService.save(pEntity);
		return  ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);
	}
	
//	Atualizar Categoria	
	@PutMapping("/{pID}")
	public ResponseEntity<Categoria> update(@PathVariable Long pID, @Valid @RequestBody Categoria pEntity) {
		Categoria categoriaSalva = categoriaService.update(pEntity);
		return  ResponseEntity.ok(categoriaSalva);
	}

//	Deletar Categoria	
	@DeleteMapping("/{pID}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(Long pID) {
		categoriaRepository.deleteById(pID);
		
	}

//	Filtro por ID
	@GetMapping("/{pID}")
	public ResponseEntity<Categoria> findById(@PathVariable Long pID) {
		return ResponseEntity.ok(categoriaRepository.findById(pID).get());
	}
	
	@PostMapping("/deleteall")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteAll( @RequestBody List<Categoria> pList) {
		categoriaRepository.deleteAll(pList);
	}


//  Listar Categoria	
	@GetMapping
	public List<Categoria> findAllList() {
		return categoriaRepository.findAll();
	}
	
	public Page<Categoria> findAllPage(Categoria pFilter, Pageable pPage) {
		return null;
	}
	
	
	
	
}
