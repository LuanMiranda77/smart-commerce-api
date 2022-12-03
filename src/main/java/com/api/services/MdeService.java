package com.api.services;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.domain.TO.ProdutoTO;
import com.api.repository.MdeRepository;

@Service
public class MdeService {

	@Autowired
	private MdeRepository repository;

	public List<ProdutoTO> getProdutosXml(JSONObject xml) throws Exception {
		List<ProdutoTO> list = new ArrayList<ProdutoTO>();
		JSONArray listXml = xml.getJSONArray("det");

		System.out.println(listXml);

		for (Object prodXml : listXml) {

			JSONObject json = (JSONObject) prodXml;

			ProdutoTO prod = new ProdutoTO();

			prod.setCodigo(json.getJSONObject("prod").getLong("cProd"));
			prod.setEan(String.valueOf(json.getJSONObject("prod").get("cEAN")));
			prod.setNome(json.getJSONObject("prod").getString("xProd"));
			prod.setNcm(String.valueOf(json.getJSONObject("prod").get("NCM")));

			if (!json.getJSONObject("prod").isNull("CEST")) {
				prod.setCest(String.valueOf(json.getJSONObject("prod").get("CEST")));
			}

			prod.setCfop(String.valueOf(json.getJSONObject("prod").get("CFOP")));
			prod.setUniCom(json.getJSONObject("prod").getString("uCom"));
			prod.setQuantCom(json.getJSONObject("prod").getDouble("qCom"));
			prod.setEanTrib(String.valueOf(json.getJSONObject("prod").get("cEANTrib")));
			prod.setUniTrib(json.getJSONObject("prod").getString("uTrib"));
			prod.setQuantTrib(json.getJSONObject("prod").getDouble("qTrib"));
			prod.setValorUnit(json.getJSONObject("prod").getFloat("vUnCom"));
			prod.setValorTotal(json.getJSONObject("prod").getFloat("vProd"));

			if (!json.getJSONObject("prod").isNull("vOutro")) {
				prod.setOutro(json.getJSONObject("prod").getFloat("vOutro"));
			}

//			impostos produto
			if (!json.getJSONObject("imposto").isNull("vTotTrib")) {
				prod.setVTotTrib(json.getJSONObject("imposto").getFloat("vTotTrib"));
			}

			boolean band = false;

			if (!json.getJSONObject("imposto").getJSONObject("ICMS").isNull("ICMSSN101")) {
				band = true;
				prod.setCstIcms(String.valueOf(
						json.getJSONObject("imposto").getJSONObject("ICMS").getJSONObject("ICMSSN101").get("CSOSN")));
			} else if (!json.getJSONObject("imposto").getJSONObject("ICMS").isNull("ICMSSN102")) {
				band = true;
				prod.setCstIcms(String.valueOf(
						json.getJSONObject("imposto").getJSONObject("ICMS").getJSONObject("ICMSSN102").get("CSOSN")));
			} else if (!json.getJSONObject("imposto").getJSONObject("ICMS").isNull("ICMSSN103")) {
				band = true;
				prod.setCstIcms(String.valueOf(
						json.getJSONObject("imposto").getJSONObject("ICMS").getJSONObject("ICMSSN103").get("CSOSN")));
			} else if (!json.getJSONObject("imposto").getJSONObject("ICMS").isNull("ICMSSN201")) {
				band = true;
				prod.setCstIcms(String.valueOf(
						json.getJSONObject("imposto").getJSONObject("ICMS").getJSONObject("ICMSSN201").get("CSOSN")));
			} else if (!json.getJSONObject("imposto").getJSONObject("ICMS").isNull("ICMSSN202")) {
				band = true;
				prod.setCstIcms(String.valueOf(
						json.getJSONObject("imposto").getJSONObject("ICMS").getJSONObject("ICMSSN202").get("CSOSN")));
			} else if (!json.getJSONObject("imposto").getJSONObject("ICMS").isNull("ICMSSN203")) {
				band = true;
				prod.setCstIcms(String.valueOf(
						json.getJSONObject("imposto").getJSONObject("ICMS").getJSONObject("ICMSSN203").get("CSOSN")));
			} else if (!json.getJSONObject("imposto").getJSONObject("ICMS").isNull("ICMSSN300")) {
				band = true;
				prod.setCstIcms(String.valueOf(
						json.getJSONObject("imposto").getJSONObject("ICMS").getJSONObject("ICMSSN300").get("CSOSN")));
			} else if (!json.getJSONObject("imposto").getJSONObject("ICMS").isNull("ICMSSN400")) {
				band = true;
				prod.setCstIcms(String.valueOf(
						json.getJSONObject("imposto").getJSONObject("ICMS").getJSONObject("ICMSSN400").get("CSOSN")));
			} else if (!json.getJSONObject("imposto").getJSONObject("ICMS").isNull("ICMSSN500")) {
				band = true;
				prod.setCstIcms(String.valueOf(
						json.getJSONObject("imposto").getJSONObject("ICMS").getJSONObject("ICMSSN500").get("CSOSN")));
			} else if (!json.getJSONObject("imposto").getJSONObject("ICMS").isNull("ICMSSN900")) {
				band = true;
				prod.setCstIcms(String.valueOf(
						json.getJSONObject("imposto").getJSONObject("ICMS").getJSONObject("ICMSSN900").get("CSOSN")));
			}

			if (!band) {
				String icms = "ICMS";
				for (int i = 0; i < 10; i++) {
					
					String prefix = icms + "" + i + "0";
					
					if (!json.getJSONObject("imposto").getJSONObject("ICMS").isNull(prefix)) {
						prod.setCstIcms("0"+String.valueOf(json.getJSONObject("imposto").getJSONObject("ICMS").getJSONObject(prefix).get("CST")));
						prod.setPorcIcms(!json.getJSONObject("imposto").getJSONObject("ICMS").getJSONObject(prefix).isNull("pICMS") ? json.getJSONObject("imposto").getJSONObject("ICMS").getJSONObject(prefix).getFloat("pICMS"):0);
						prod.setValorIcms(!json.getJSONObject("imposto").getJSONObject("ICMS").getJSONObject(prefix).isNull("vICMS") ? json.getJSONObject("imposto").getJSONObject("ICMS").getJSONObject(prefix).getFloat("vICMS"):0);
						prod.setPorcIcmsSt(!json.getJSONObject("imposto").getJSONObject("ICMS").getJSONObject(prefix).isNull("pICMSST") ? json.getJSONObject("imposto").getJSONObject("ICMS").getJSONObject(prefix).getFloat("pICMSST"):0);
						prod.setValorIcmsSt(!json.getJSONObject("imposto").getJSONObject("ICMS").getJSONObject(prefix).isNull("pICMSST") ? json.getJSONObject("imposto").getJSONObject("ICMS").getJSONObject(prefix).getFloat("vICMSST"):0);
					} else if (!json.getJSONObject("imposto").isNull("ICMS41")) {
						prod.setCstIcms("0"+String.valueOf(json.getJSONObject("imposto").getJSONObject("ICMS").getJSONObject("ICMS41").get("CST")));
						prod.setPorcIcms(!json.getJSONObject("imposto").getJSONObject("ICMS").getJSONObject("ICMS41").isNull("pICMS") ? json.getJSONObject("imposto").getJSONObject("ICMS").getJSONObject("ICMS41").getFloat("pICMS"):0);
						prod.setValorIcms(!json.getJSONObject("imposto").getJSONObject("ICMS").getJSONObject("ICMS41").isNull("vICMS") ? json.getJSONObject("imposto").getJSONObject("ICMS").getJSONObject("ICMS41").getFloat("vICMS"):0);
						prod.setPorcIcmsSt(!json.getJSONObject("imposto").getJSONObject("ICMS").getJSONObject("ICMS41").isNull("pICMSST") ? json.getJSONObject("imposto").getJSONObject("ICMS").getJSONObject("ICMS41").getFloat("pICMSST"):0);
						prod.setValorIcmsSt(!json.getJSONObject("imposto").getJSONObject("ICMS").getJSONObject("ICMS41").isNull("pICMSST") ? json.getJSONObject("imposto").getJSONObject("ICMS").getJSONObject("ICMS41").getFloat("vICMSST"):0);
					} else if (!json.getJSONObject("imposto").isNull("ICMS651")) {
						prod.setCstIcms(String.valueOf(json.getJSONObject("imposto").getJSONObject("ICMS").getJSONObject("ICMS651").get("CST")));
						prod.setPorcIcms(!json.getJSONObject("imposto").getJSONObject("ICMS").getJSONObject("ICMS651").isNull("pICMS") ? json.getJSONObject("imposto").getJSONObject("ICMS").getJSONObject("ICMS651").getFloat("pICMS"):0);
						prod.setValorIcms(!json.getJSONObject("imposto").getJSONObject("ICMS").getJSONObject("ICMS651").isNull("vICMS") ? json.getJSONObject("imposto").getJSONObject("ICMS").getJSONObject("ICMS651").getFloat("vICMS"):0);
						prod.setPorcIcmsSt(!json.getJSONObject("imposto").getJSONObject("ICMS").getJSONObject("ICMS651").isNull("pICMSST") ? json.getJSONObject("imposto").getJSONObject("ICMS").getJSONObject("ICMS651").getFloat("pICMSST"):0);
						prod.setValorIcmsSt(!json.getJSONObject("imposto").getJSONObject("ICMS").getJSONObject("ICMS651").isNull("pICMSST") ? json.getJSONObject("imposto").getJSONObject("ICMS").getJSONObject("ICMS651").getFloat("vICMSST"):0);
					}
				}
			}
			
			if (!json.getJSONObject("imposto").isNull("IPI")) {
				if (!json.getJSONObject("imposto").getJSONObject("IPI").isNull("IPITrib")) {
					prod.setCstIpi(String.valueOf(json.getJSONObject("imposto").getJSONObject("IPI").getJSONObject("IPITrib").get("CST")));
					prod.setPorcIpi(json.getJSONObject("imposto").getJSONObject("IPI").getJSONObject("IPITrib").getFloat("pIPI"));
					prod.setValorIpi(json.getJSONObject("imposto").getJSONObject("IPI").getJSONObject("IPITrib").getFloat("vIPI"));
				} 
			}

			if (!json.getJSONObject("imposto").isNull("PIS")) {
				if (!json.getJSONObject("imposto").getJSONObject("PIS").isNull("PISOutr")) {
					prod.setCstPis(String.valueOf(json.getJSONObject("imposto").getJSONObject("PIS").getJSONObject("PISOutr").get("CST")));
				}else if (!json.getJSONObject("imposto").getJSONObject("PIS").isNull("PISNT")) {
					prod.setCstPis(String.valueOf(json.getJSONObject("imposto").getJSONObject("PIS").getJSONObject("PISNT").get("CST")));
				} else if (!json.getJSONObject("imposto").getJSONObject("PIS").isNull("PISAliq")) {
					prod.setCstPis(String.valueOf(json.getJSONObject("imposto").getJSONObject("PIS").getJSONObject("PISAliq").get("CST")));
					prod.setPorcPis(json.getJSONObject("imposto").getJSONObject("PIS").getJSONObject("PISAliq").getFloat("pPIS"));
					prod.setValorPis(json.getJSONObject("imposto").getJSONObject("PIS").getJSONObject("PISAliq").getFloat("vPIS"));
				}

			}
			
			if (!json.getJSONObject("imposto").isNull("COFINS")) {
				if (!json.getJSONObject("imposto").getJSONObject("COFINS").isNull("COFINSOutr")) {
					prod.setCstCofins(String.valueOf(json.getJSONObject("imposto").getJSONObject("COFINS").getJSONObject("COFINSOutr").get("CST")));
				}else if (!json.getJSONObject("imposto").getJSONObject("COFINS").isNull("COFINSNT")) {
					prod.setCstPis(String.valueOf(json.getJSONObject("imposto").getJSONObject("COFINS").getJSONObject("COFINSNT").get("CST")));
				} else if (!json.getJSONObject("imposto").getJSONObject("COFINS").isNull("COFINSAliq")) {
					prod.setCstCofins(String.valueOf(json.getJSONObject("imposto").getJSONObject("COFINS").getJSONObject("COFINSAliq").get("CST")));
					prod.setPorcPis(json.getJSONObject("imposto").getJSONObject("COFINS").getJSONObject("COFINSAliq").getFloat("pCOFINS"));
					prod.setValorPis(json.getJSONObject("imposto").getJSONObject("COFINS").getJSONObject("COFINSAliq").getFloat("vCOFINS"));
				}
			}
			
			list.add(prod);
		}

		return list;
	}

}
