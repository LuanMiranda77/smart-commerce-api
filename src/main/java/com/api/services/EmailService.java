package com.api.services;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	public void sendEmail(String email, String title, String mensagem) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(email);
		msg.setSubject(title);
		msg.setText("Ecomerce Loja"+ "\n\r" + mensagem);
		javaMailSender.send(msg);
	}

	public void sendEmailSimples(String email, String title, String mensagem) throws MessagingException {
		// instaciando objeto da mensagem para o envio
		MimeMessage msg = javaMailSender.createMimeMessage();

		// instaciando as partes da menssagem
		MimeMessageHelper helper = new MimeMessageHelper(msg, true);
//		      aqui colocamos o destinatario do email
		helper.setTo(email);
		// titulo do email
		helper.setSubject(title);

		// codigo para colocar o texto em html no email
		helper.setText(modeloEmailHTML(mensagem), true);
		javaMailSender.send(msg);
	}

	public void sendEmailComAnexo(String email, String title, String mensagem, File anexo)
			throws MessagingException, IOException {
		// instaciando objeto da mensagem para o envio
		MimeMessage msg = javaMailSender.createMimeMessage();

		// instaciando as partes da menssagem
		MimeMessageHelper helper = new MimeMessageHelper(msg, true);
//      aqui colocamos o destinatario do email
		helper.setTo(email);
// titulo do email
		helper.setSubject(title);

// codigo para colocar o texto em html no email
		helper.setText(modeloEmailHTML(mensagem), true);

//nesta linha fica o anexo do email a ser enviado
		helper.addAttachment(anexo.getName(), new FileSystemResource(anexo));

//comando de envio
		javaMailSender.send(msg);

	}
	
	public boolean sendEmailMultiplosDestinatarios(List<String> destinatarios, String title, String mensagem, File anexo ) {
		
		if(anexo != null) {
			destinatarios.forEach(destino -> {
				try {
					sendEmailComAnexo(destino, title, mensagem, anexo);
				} catch (MessagingException | IOException e) {
					e.printStackTrace();
				}
			});
		}else {
			destinatarios.forEach(destino -> {
				try {
					sendEmailSimples(destino, title, mensagem);
				} catch (MessagingException e) {
					e.printStackTrace();
				}
			});
		}
		
		return false;
		
	}
	
	public String modeloEmailHTML(String menssagem) {
		return"<!DOCTYPE html>"
				+ "<html>"
				+ "<body>"
				+ "<table align=left cellpadding=0 cellspacing=0 width=600>"
				+ "<tr>"
				+ "<td style='padding: 40px 0 30px 0;'>"
				+ "<img src='https://upload.wikimedia.org/wikipedia/commons/a/ab/Logo_TV_2015.png' "
				+ "alt='Criando Mágica de E-mail'"
				+ "width='100' height='100' style='display: block;' />"
				+ "</td>"
				+ "</tr>"
				+ "<tr>"
				+ "  <td>"
				+ "  	<h1>E-commerce protal</h1>"
				+ "    <h5>fazendo da sua vida melhor</h5>"
				+ "  </td>"
				+ "</tr>"
				+ "<tr>"
				+ "<td>"
				+ menssagem 
				+ "</td>"
				+ "</tr>"
				+ "<tr>"
				+ "<td>"
				+ " <h6>"
				+ "  Algumas informações de copyright ou talvez alguma informação do autor de um "//colocar mensage do rodápe aqui
				+ "</h6>"
				+ "</td>"
				+ "</tr>"
				+ "</table>"
				+ "</body>"
				+ "</html>";
	}

}
