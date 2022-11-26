package com.api.resources;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.api.domain.TO.DocUploadTo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.xml.bind.annotation.XmlAccessOrder;

@RestController
@RequestMapping("/api/upload")
@MultipartConfig
public class UploadResource {
	
	@Autowired
	private ServletContext servletContext;
	
//	@PostMapping
//	public void upload(@RequestBody DocUploadTo doc, HttpServletResponse response)
//			throws ServletException, IOException {
//		
//		String path = servletContext.getRealPath("\\");
//		String pasta = "\\nfe\\entrada";
//		
//		// salvando no computador
//		File diretorio = new File(path+"\\nfe");
//		if(!diretorio.isDirectory()) {
//			diretorio.mkdir();
//		}
//		
//		diretorio = new File(path+"\\nfe\\entrada");
//		if(!diretorio.isDirectory()) {
//			diretorio.mkdir();
//		}
//		
//		Path filepath = Paths.get(path+pasta, doc.getFiles().get(0).getOriginalFilename());
//		doc.getFiles().get(0).transferTo(filepath);
//	}
	
	@PostMapping
	public void upload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		Part filePart;
		try {
			// obtendo o arquivo, semelhante ao getParameter
			filePart = request.getPart("file");
			System.out.println(filePart.getContentType());
			// obtendo o arquivo, semelhante ao estabelecimento
			String estabelecimento = request.getPart("estabelecimento").getSubmittedFileName();
			
			if(filePart.getContentType().equals("text/xml")) {
				uploadXML(filePart, estabelecimento);
			}else {
				uploadImage(filePart, estabelecimento);
			}
		}catch (Exception e) {
			response.sendError(400);
		}
		
		
	}
	
	private void uploadXML(Part filePart, String estabelecimento) throws IOException {
		String path = servletContext.getRealPath("/");
		String pasta = "/upload/"+estabelecimento+"/nfe/entrada/";
		
		// salvando no computador
		File diretorio = new File(path + "/upload");
		if (!diretorio.isDirectory()) {
			diretorio.mkdir();
		}
		
		diretorio = new File(path + "/upload/"+estabelecimento);
		if (!diretorio.isDirectory()) {
			diretorio.mkdir();
		}
		
		diretorio = new File(path + "/upload/"+estabelecimento+"/nfe");
		if (!diretorio.isDirectory()) {
			diretorio.mkdir();
		}

		diretorio = new File(path + "/upload/"+estabelecimento+"/nfe/entrada");
		if (!diretorio.isDirectory()) {
			diretorio.mkdir();
		}
		
		filePart.write(path + pasta + filePart.getSubmittedFileName());
	}
	
	private void uploadImage(Part filePart, String estabelecimento) throws IOException {
		String path = servletContext.getRealPath("/");
		String pasta = "/upload/"+estabelecimento+"/images/";
		
		// salvando no computador
		File diretorio = new File(path + "/upload");
		if (!diretorio.isDirectory()) {
			diretorio.mkdir();
		}
		
		diretorio = new File(path + "/upload/"+estabelecimento);
		if (!diretorio.isDirectory()) {
			diretorio.mkdir();
		}
		
		diretorio = new File(path + "/upload/"+estabelecimento+"/images");
		if (!diretorio.isDirectory()) {
			diretorio.mkdir();
		}

		filePart.write(path + pasta + filePart.getSubmittedFileName());
	}
	
	
	
}
