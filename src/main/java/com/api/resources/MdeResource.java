package com.api.resources;

import java.awt.print.Pageable;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.api.domain.Mde;
import com.api.domain.TO.ProdutoTO;
import com.api.repository.MdeRepository;
import com.api.services.MdeService;
import com.api.utils.UtilsConvert;

//@autor Jadson Feitosa #AE-40

@RestController
@RequestMapping(value = "api/mde")
public class MdeResource implements ResourceBase<Mde, Long> {

	@Autowired
	private MdeRepository mdeRepository;
	
	@Autowired
	private ServletContext servletContext;
	
	@Autowired
	private MdeService service;

//	Salvar Mde 
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Mde> save(@Valid Mde pEntity, HttpServletResponse response) {
		Mde MdeSalvo = mdeRepository.save(pEntity);
		return ResponseEntity.status(HttpStatus.CREATED).body(MdeSalvo);
	}

//	Atualizar Mde
	@PutMapping
	public ResponseEntity<Mde> update(@Valid Long pID, Mde pEntity) {
		Mde MdeSalvo = mdeRepository.save(pEntity);
		return ResponseEntity.ok(MdeSalvo);
	}

//	Atualizar status

//	Deletar Mde
	@DeleteMapping("/{pID}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(Long pID) {
		mdeRepository.deleteById(pID);

	}
	
	@GetMapping
	public List<Mde> findAllList() {
		return null;
	}
	
	@GetMapping("/produtosxml")
	public List<ProdutoTO> findListProdutosXML(HttpServletRequest request, HttpServletResponse response) throws ServletException{
		List<ProdutoTO> list = new ArrayList<ProdutoTO>();
		String pathBase = servletContext.getRealPath("/");
		
		String estabelecimento = request.getParameter("est");
		
		String chave = request.getParameter("chave");
		
		String pasta = "/upload/"+estabelecimento+"/nfe/entrada/";
		
		File file = new File(pathBase+pasta+chave+".xml");
		String xml;
		
		try {
			xml = new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);
//		System.err.println(xml);
			JSONObject json  = XML.toJSONObject(xml).getJSONObject("nfeProc").getJSONObject("NFe").getJSONObject("infNFe");
			System.out.println(json);
			list = service.getProdutosXml(json);
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}



//	Listar Mdes
	@GetMapping("{pId}/{dtIni}/{dtFin}/{tipo}")
	public List<Mde> findListEmissao(@PathVariable Long pId, @PathVariable String dtIni, @PathVariable String dtFin, @PathVariable String tipo) {
		Date ini = UtilsConvert.convertStringByDate(dtIni);
		Date fin = UtilsConvert.convertStringByDate(dtFin);
		if(tipo.equals("1")) {
			return  mdeRepository.findNotaByEstabelecimentoAndEmisao(pId, ini, fin);
		}else if(tipo.equals("2")) {
			return  mdeRepository.findNotaByEstabelecimentoAndManifesto(pId, ini, fin);
		}else {
			return  mdeRepository.findNotaByEstabelecimentoAndEntrada(pId, ini, fin);
		}
	}

	@Override
	public ResponseEntity<Mde> findById(Long pID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Mde> findAllPage(Mde pFilter, Pageable pPage) {
		// TODO Auto-generated method stub
		return null;
	}

}
