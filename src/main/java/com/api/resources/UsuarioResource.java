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

import com.api.domain.Pedido;
import com.api.domain.Usuario;
import com.api.domain.TO.UserLoginTO;
import com.api.domain.enuns.EstatusPedido;
import com.api.domain.enuns.EstatusUsuario;
import com.api.repository.UsuarioRepository;
import com.api.resources.exception.LoginException;
import com.api.services.UsuarioService;

//@autor Jadson Feitosa #AE-42

@RestController
@RequestMapping("/api/usuario")
public class UsuarioResource implements ResourceBase<Usuario, Long> {
	
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired 
	private UsuarioRepository usuarioRepository;
	
	@PostMapping("/login")
	public ResponseEntity<Usuario> login(@RequestBody UserLoginTO pEntity, HttpServletResponse response){
		Usuario userSalvo = usuarioRepository.findByEmail(pEntity.getEmail());
		
		if(userSalvo != null) {
			if(userSalvo.getEmail().equals(pEntity.getEmail()) 
					&& userSalvo.getPassword().equals(pEntity.getPassword())) {
				
				return ResponseEntity.status(HttpStatus.ACCEPTED).body(userSalvo);
				
			}else {
				throw new LoginException();
			}
		}else {
			throw new LoginException();
		}
	}
	
	
//	Salvar Usuario
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Usuario> save(@Valid @RequestBody Usuario pEntity, HttpServletResponse response) {
		Usuario usuarioSalvo = usuarioService.save(pEntity);
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioSalvo);
	}
	
//	recuperar senha 
//	--ADD --Luan Miranda - AE-10---------------------------------------------
	@PostMapping("/recuperasenha")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public ResponseEntity<Boolean> recuperaSenha(@RequestBody Usuario pEntity, HttpServletResponse response) {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(usuarioService.recuperaSenha(pEntity.getEmail()));
	}
	
	@PostMapping("/email-marketing")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public ResponseEntity<Boolean> enviarEmailMassa(@RequestBody List<String> emails, HttpServletResponse response) {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(usuarioService.enviarEmailMarketing(emails));
	}
//----------------------------------fim--------------------------------------------	
	
	
//	Atualizar Usuario
	@PutMapping("/{pID}")
	public ResponseEntity<Usuario> update(@PathVariable Long pID, @Valid @RequestBody Usuario pEntity) {
		Usuario usuarioSalvo = usuarioService.update(pEntity);
		return ResponseEntity.ok(usuarioSalvo);
	}
	
//	Atualizar status
	@PutMapping("/status/{id}/{status}")
	public ResponseEntity<Usuario> updateStatus(@PathVariable Long id, @PathVariable EstatusUsuario status) {
		usuarioRepository.updateStatus(id,status);
		return ResponseEntity.ok(null);
	}

//	Deletar Usuario
	@DeleteMapping("/{pID}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(Long pID) {
		usuarioRepository.deleteById(pID);
	}

//	Filtro por ID
	@GetMapping("/{pID}")
	public ResponseEntity<Usuario> findById(@PathVariable Long pID) {
		Usuario usuarioSalvo = usuarioRepository.findById(pID).get();
		usuarioSalvo.setPassword(null);
		return ResponseEntity.ok(usuarioSalvo);
	}

//  Listar usuario
	@GetMapping
	public List<Usuario> findAllList() {
		return usuarioRepository.findAll();
	}

	public Page<Usuario> findAllPage(Usuario pFilter, Pageable pPage) {
		return null;
	}

}
