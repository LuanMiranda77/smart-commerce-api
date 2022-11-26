package com.api.resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/download")
public class DowloadResource extends HttpServlet  {
	
	@Autowired
	private ServletContext servletContext;
	
	@GetMapping
	public void dowload(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
		
		String pathBase = servletContext.getRealPath("/");
		
		String estabelecimento = request.getParameter("est");
		
		String pasta = "/upload/"+estabelecimento+"/nfe/entrada/";
		
		String codigo = request.getParameter("c");
    	String content_type = request.getParameter("ct");
    	
    	if(!content_type.equals("xml")) {
    		pasta = "/upload/"+estabelecimento+"/images/";
    	}
    	
    	File file = new File("C:\\Users\\Menew\\Documents\\xmls teste\\Xml arte cozinha\\33220200074569000100550100003043831231851691.xml");
		String xml = new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);
//		System.err.println(xml);
		JSONObject json  = XML.toJSONObject(xml);
		System.out.println(json.getJSONObject("nfeProc").getJSONObject("NFe").getJSONObject("infNFe").getJSONObject("emit"));
//		String jsonString = json.toString(4);  
//	    System.err.println(jsonString); 
    	
		try{
    		byte[] docData = carregarDoc(pathBase+pasta+codigo+"."+content_type);
    		
        	response.setContentType(content_type);
        	response.getOutputStream().write(docData);
        	
    	}catch (Exception e) {
    		response.sendError(404, "frrgwerer");
    	}
    	
//    	response.setContentType("doc/png");
		
	}
	
	private static byte[] carregarDoc(String path) throws FileNotFoundException, IOException {
        byte[] doc;
        File file = new File(path);
        doc = new byte[(int)file.length()];

        FileInputStream fileInputStream = new FileInputStream(file);
        fileInputStream.read(doc);

        fileInputStream.close();
        return doc;
    }

}
