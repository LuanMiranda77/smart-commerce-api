package com.api.services;



import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.domain.Cliente;
import com.api.domain.Endereco;
import com.api.domain.Usuario;
import com.api.repository.ClienteRepository;

//@autor Jadson Feitosa #AE-40

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	
	public Cliente save(Cliente pEntity) {
		if(pEntity.getUsuario().getId() == 0) {
			pEntity.getUsuario().setId(null);
			String senha = pEntity.getUsuario().getPassword();
			long id = usuarioService.save(pEntity.getUsuario()).getId();
			pEntity.getUsuario().setId(id);
			pEntity.getUsuario().setPassword(senha);
		}
		return clienteRepository.save(pEntity);
	}
	
	public Cliente update(Cliente pEntity) {
		Cliente clienteSalvo = clienteRepository.findById(pEntity.getId()).get();
		
		BeanUtils.copyProperties(pEntity, clienteSalvo, "id");
		clienteRepository.save(clienteSalvo);
		clienteSalvo.setId(pEntity.getId());
		return clienteSalvo;
		
	}
	
	public Cliente getByUser(Long id) {
		Usuario user = usuarioService.findById(id);
		return clienteRepository.findByUsuario(user);
	}
	
	public void isAtive(Cliente pEntity) {
		update(pEntity);
	}
	
	public void salvaEndereços(List<Endereco> enderecos) {
		
	}
		
}
