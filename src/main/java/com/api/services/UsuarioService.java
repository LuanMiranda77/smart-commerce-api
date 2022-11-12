package com.api.services;

import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.domain.Usuario;
import com.api.domain.TO.UsuarioTO;
import com.api.repository.UsuarioRepository;
import com.api.services.exceptions.EmailNotExistException;
import com.api.services.exceptions.UsuarioExistException;

//@autor Jadson Feitosa #AE-42

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private EmailService emailService;
	
	public Usuario save(Usuario pEntity) {
		Usuario userSalvo =null;
		
//		if(usuarioRepository.existsByEmail(pEntity.getEmail())) {
//			throw new UsuarioExistException();
//		}
		
		Long codigo = usuarioRepository.findMaxCodigoByEstabelecimento(pEntity.getEstabelecimento().getId());
		if(codigo==null) {
			codigo=1l;
		}else {
			codigo+=1l;
		}
		pEntity.setCodigo(codigo);
		userSalvo = usuarioRepository.save(pEntity);
		userSalvo.setPassword(null);
		
		return pEntity;
		
	}
	
	public Usuario update( Usuario pEntity) {
		Usuario usuarioSalvo = usuarioRepository.findById(pEntity.getId()).get();
		
		BeanUtils.copyProperties(pEntity, usuarioSalvo,"id");
		usuarioSalvo = usuarioRepository.save(usuarioSalvo);
		
		return usuarioSalvo;
	}
	
	public void isAtive(Usuario pEntity) {
		update(pEntity);
	}
	
	public boolean recuperaSenha(String email) {
		Usuario user = usuarioRepository.findByEmail(email);
		if(user != null) {
			try {
				emailService.sendEmailSimples(user.getEmail(), "Recuperação de senha", "Caro(a) "+user.getNome()+",\n\r"
						+ "você solicitou a recuperação de sua senha de acesso do portal. \n\r"
						+ "caso se esqueceu da senha ela é: "+user.getPassword());
			} catch (MessagingException e) {
				e.printStackTrace();
			}
			return true;

		}else {
			throw new  EmailNotExistException();
		}
	}
	
	public boolean enviarEmailMarketing(List<String> emails) {
		emailService.sendEmailMultiplosDestinatarios(emails, "teste", "ai dentro de jadson", null);
		return true;
	}
	
	public void testeExistUsuario(String email) {
		if(!usuarioRepository.existsByEmail(email)) {
			throw new  EmailNotExistException();
		}
		
	}
	
	public Usuario findById(Long id) {
		return usuarioRepository.getById(id);
	}

}
