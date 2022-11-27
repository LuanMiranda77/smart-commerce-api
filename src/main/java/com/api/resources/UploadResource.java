package com.api.resources;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.domain.Estabelecimento;
import com.api.domain.Fornecedor;
import com.api.domain.Mde;
import com.api.repository.EstabelecimentoRepository;
import com.api.repository.FornecedorRepository;
import com.api.repository.MdeRepository;
import com.api.utils.UtilsConvert;

@RestController
@RequestMapping("/api/upload")
@MultipartConfig
public class UploadResource {

	@Autowired
	private ServletContext servletContext;

	@Autowired
	private EstabelecimentoRepository estRepository;

	@Autowired
	private FornecedorRepository fornecedorRepository;

	@Autowired
	private MdeRepository mdeRepository;

	@PostMapping
	public void upload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Part filePart;
//		try {
		// obtendo o arquivo, semelhante ao getParameter
		filePart = request.getPart("file");

		// obtendo o arquivo, semelhante ao estabelecimento
		String estabelecimento = request.getPart("estabelecimento").getSubmittedFileName();

		Estabelecimento est = estRepository.getById(Long.parseLong(estabelecimento));

		if (filePart.getContentType().equals("text/xml")) {

			uploadXML(filePart, estabelecimento);

			Boolean band = false;

			String path = servletContext.getRealPath("/");
			String pasta = "/upload/" + estabelecimento + "/nfe/entrada/";
			String pathOrigin = path + pasta;
			System.err.println(pathOrigin);

			File file = new File(pathOrigin + filePart.getSubmittedFileName());
			String xml = new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);

			// converter xml em json
			JSONObject json = XML.toJSONObject(xml);

			if (json.isNull("nfeProc")) {
				delArquivo(pathOrigin + filePart.getSubmittedFileName());
				band = true;
				response.sendError(406, "Arquivo de xml inválido&");
			}

			if (!band) {

				JSONObject objNfe = json.getJSONObject("nfeProc").getJSONObject("NFe").getJSONObject("infNFe");
				JSONObject objEmitente = json.getJSONObject("nfeProc").getJSONObject("NFe").getJSONObject("infNFe")
						.getJSONObject("emit");
				JSONObject objAutorizacao = json.getJSONObject("nfeProc").getJSONObject("protNFe")
						.getJSONObject("infProt");

				String cnpjCpf = "";
				if (!objEmitente.isNull("CPF")) {
					cnpjCpf = "" + objEmitente.getString("CPF");
				} else {
					cnpjCpf = "" + objEmitente.get("CNPJ");
				}

				String messageErro = cnpjCpf + "&" + objEmitente.getString("xNome") + "&" + objAutorizacao.get("chNFe")
						+ "&" + objNfe.getJSONObject("total").getJSONObject("ICMSTot").getFloat("vNF") + "&"
						+ objNfe.getJSONObject("ide").get("dhEmi");

				if (json.getJSONObject("nfeProc").getJSONObject("NFe").isNull("Signature")
						|| json.getJSONObject("nfeProc").isNull("protNFe")) {
					delArquivo(pathOrigin + filePart.getSubmittedFileName());
					band = true;
					response.sendError(406,
							"Não foi possível importar o XML. Arquivo inválido, falta a assinatura digital e/ou o protocolo!&"
									+ messageErro);
				} else if (objNfe.getJSONObject("ide").getInt("mod") != 55) {
					delArquivo(pathOrigin + filePart.getSubmittedFileName());
					band = true;
					response.sendError(406, "Não foi possível importar o XML. Modelo de nota inválido!&" + messageErro);
				} else if (!objNfe.getJSONObject("ide").isNull("tpAmb")
						&& objNfe.getJSONObject("ide").getInt("tpAmb") != 1) {
					delArquivo(pathOrigin + filePart.getSubmittedFileName());
					band = true;
					response.sendError(406,
							"Não foi possível importar o XML. A nota foi emitida em ambiente de homologação!&"
									+ messageErro);
				} else if (cnpjCpf.equals(est.getCnpjCpf())) {
					delArquivo(pathOrigin + filePart.getSubmittedFileName());
					band = true;
					response.sendError(406,
							"Não foi possível importar o XML. O CNPJ/CPF do emitente é igual ao CNPJ/CPF do seu estabelecimento!&"
									+ messageErro);
				} else if (!objNfe.getJSONObject("dest").isNull("CNPJ")
						&& !est.getCnpjCpf().equals("" + objNfe.getJSONObject("dest").get("CNPJ"))) {
					delArquivo(pathOrigin + filePart.getSubmittedFileName());
					band = true;
					response.sendError(406,
							"Não foi possível importar o XML. CNPJ do destinatário difere do CNPJ do seu estabelecimento!&"
									+ messageErro);
				} else if (!objNfe.getJSONObject("dest").isNull("CPF")
						&& !est.getCnpjCpf().equals("" + objNfe.getJSONObject("dest").get("CPF"))) {
					delArquivo(pathOrigin + filePart.getSubmittedFileName());
					band = true;
					response.sendError(406,
							"Não foi possível importar o XML. CPF do destinatário difere do CPF do seu estabelecimento!&"
									+ messageErro);
				}

				if (!band) {
					Fornecedor forn = new Fornecedor();
					forn.setCnpjCpf(cnpjCpf);
					if (cnpjCpf.length() > 11) {
						forn.setInstEstadual("" + objEmitente.get("IE"));
					}
					forn.setRazao(objEmitente.getString("xNome"));
					forn.setNomeFatasia(
							!objEmitente.getJSONObject("enderEmit").isNull("xFant") ? objEmitente.getString("xFant")
									: "");
					forn.setCep("" + objEmitente.getJSONObject("enderEmit").getInt("CEP"));
					forn.setLogradouro(objEmitente.getJSONObject("enderEmit").getString("xLgr"));
					forn.setNumero("" + objEmitente.getJSONObject("enderEmit").get("nro"));
					forn.setBairro(objEmitente.getJSONObject("enderEmit").getString("xBairro"));
					forn.setCidade(objEmitente.getJSONObject("enderEmit").getString("xMun"));
					forn.setUf(objEmitente.getJSONObject("enderEmit").getString("UF"));
					forn.setCodIbge("" + objEmitente.getJSONObject("enderEmit").getInt("cMun"));

					if (!objEmitente.getJSONObject("enderEmit").isNull("fone")) {
						forn.setFone("" + objEmitente.getJSONObject("enderEmit").getInt("fone"));
					}
					if (!objEmitente.getJSONObject("enderEmit").isNull("email")) {
						forn.setEmail("" + objEmitente.getJSONObject("enderEmit").getInt("email"));
					}

					forn.setEstabelecimento(Long.parseLong(estabelecimento));

					fornecedorRepository.save(forn);

					Mde mde = new Mde();
					mde.setEstabelecimento(Long.parseLong(estabelecimento));
					mde.setNumNota(Long.parseLong("" + objNfe.getJSONObject("ide").getInt("cNF")));
					mde.setSerie(objNfe.getJSONObject("ide").getInt("serie"));
					mde.setChaveAcesso("" + objAutorizacao.get("chNFe"));
					mde.setDataEmissao(UtilsConvert.convertStringByDate(objNfe.getJSONObject("ide").getString("dhEmi").substring(0, 10)));
					mde.setCnpjCpf(cnpjCpf);
					mde.setFornecedor(forn.getRazao());
					mde.setValorTotalNota(objNfe.getJSONObject("total").getJSONObject("ICMSTot").getFloat("vNF"));
					mde.setValorDesc(objNfe.getJSONObject("total").getJSONObject("ICMSTot").getFloat("vDesc"));
					mde.setValorTotalNotaLiquido(
							objNfe.getJSONObject("total").getJSONObject("ICMSTot").getFloat("vProd"));
					mde.setValorBaseIcms(objNfe.getJSONObject("total").getJSONObject("ICMSTot").getFloat("vBC"));
					mde.setValorIcms(objNfe.getJSONObject("total").getJSONObject("ICMSTot").getFloat("vICMS"));
					mde.setValorBaseSubTributa(
							objNfe.getJSONObject("total").getJSONObject("ICMSTot").getFloat("vBCST"));
					mde.setValorSubTributa(objNfe.getJSONObject("total").getJSONObject("ICMSTot").getFloat("vST"));
					mde.setValorPis(objNfe.getJSONObject("total").getJSONObject("ICMSTot").getFloat("vPIS"));
					mde.setValorIpi(objNfe.getJSONObject("total").getJSONObject("ICMSTot").getFloat("vIPI"));
					mde.setValorCofins(objNfe.getJSONObject("total").getJSONObject("ICMSTot").getFloat("vCOFINS"));
					mde.setValorFrete(objNfe.getJSONObject("total").getJSONObject("ICMSTot").getFloat("vFrete"));
					mde.setValorOutros(objNfe.getJSONObject("total").getJSONObject("ICMSTot").getFloat("vOutro"));
					mde.setNumProtocolo("" + objAutorizacao.get("nProt"));
					mde.setStatus("M");

					mdeRepository.save(mde);
					
				}
			}

		} else {
			uploadImage(filePart, estabelecimento);
		}
//		} catch (Exception e) {
//			response.sendError(400);
//		}

	}

	private void uploadXML(Part filePart, String estabelecimento) throws IOException {
		String path = servletContext.getRealPath("/");
		String pasta = "/upload/" + estabelecimento + "/nfe/entrada/";
		String pathOrigin = path + pasta;

		// salvando no computador
		File diretorio = new File(path + "/upload");
		if (!diretorio.isDirectory()) {
			diretorio.mkdir();
		}

		diretorio = new File(path + "/upload/" + estabelecimento);
		if (!diretorio.isDirectory()) {
			diretorio.mkdir();
		}

		diretorio = new File(path + "/upload/" + estabelecimento + "/nfe");
		if (!diretorio.isDirectory()) {
			diretorio.mkdir();
		}

		diretorio = new File(path + "/upload/" + estabelecimento + "/nfe/entrada");
		if (!diretorio.isDirectory()) {
			diretorio.mkdir();
		}

		filePart.write(pathOrigin + filePart.getSubmittedFileName());
	}

	private void uploadImage(Part filePart, String estabelecimento) throws IOException {
		String path = servletContext.getRealPath("/");
		String pasta = "/upload/" + estabelecimento + "/images/";

		// salvando no computador
		File diretorio = new File(path + "/upload");
		if (!diretorio.isDirectory()) {
			diretorio.mkdir();
		}

		diretorio = new File(path + "/upload/" + estabelecimento);
		if (!diretorio.isDirectory()) {
			diretorio.mkdir();
		}

		diretorio = new File(path + "/upload/" + estabelecimento + "/images");
		if (!diretorio.isDirectory()) {
			diretorio.mkdir();
		}

		filePart.write(path + pasta + filePart.getSubmittedFileName());
	}

	private void delArquivo(String pathNome) {
		File file = new File(pathNome);
		if (file.exists()) {
			file.delete();
		}
	}

}
