package com.api.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.domain.TO.ProdutoTO;
import com.api.repository.MdeRepository;

//@autor Jadson Feitosa #AE-39

@Service
public class MdeService {
	

	@Autowired
	private MdeRepository repository;
	
	public List<ProdutoTO> getProdutosXml(JSONObject xml) throws Exception{
		List<ProdutoTO> list = new ArrayList<ProdutoTO>();
		JSONArray listXml = xml.getJSONArray("det");
		
		for(Object prodXml: listXml) {
			JSONObject json = (JSONObject) prodXml;
			ProdutoTO prod = new ProdutoTO();
			prod.setCProd(json.getJSONObject("prod").getLong("cProd"));
			list.add(prod);
		}
		
		return list;
	}
	
	
	
}
