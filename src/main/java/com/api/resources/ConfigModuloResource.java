package com.api.resources;

import java.awt.print.Pageable;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.api.domain.ConfigModulo;
import com.api.repository.ConfigModuloRepository;

@RestController
@RequestMapping("/api/modulo")
public class ConfigModuloResource implements ResourceBase<ConfigModulo, Long> {
	
	@Autowired
	ConfigModuloRepository repository;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<ConfigModulo> save(@Valid ConfigModulo pEntity, HttpServletResponse response) {
		return  ResponseEntity.status(HttpStatus.CREATED).body(repository.save(pEntity));
	}

	@Override
	public ResponseEntity<ConfigModulo> update(@Valid Long pID, ConfigModulo pEntity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long pID) {
		// TODO Auto-generated method stub
	}

	@Override
	public ResponseEntity<ConfigModulo> findById(Long pID) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@GetMapping("/{pID}")
	public ResponseEntity<ConfigModulo> findByEstabelecimento(@PathVariable Long pID) {
		ConfigModulo modulo = repository.findByEstabelecimento(pID);
		System.err.println(modulo);
		if(modulo == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(modulo);
	}

	@Override
	public Page<ConfigModulo> findAllPage(ConfigModulo pFilter, Pageable pPage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ConfigModulo> findAllList() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

}
