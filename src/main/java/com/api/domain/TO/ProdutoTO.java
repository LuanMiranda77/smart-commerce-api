package com.api.domain.TO;

import lombok.Data;

@Data
public class ProdutoTO {
	
	Long codigo;
	String nome;
	String ean;
	String ncm;
	String cest;
	String cfop;
	String uniCom;
	double quantCom;
	float valorUnit;
	float valorTotal;
	String eanTrib;
	String uniTrib;
	Double quantTrib;
	float desc;
	float outro;
	
//	impostos
	
	float vTotTrib;
	
	String cstIcms;
	float pIcms;
	float vIcms;
	float pIcmsSt;
	float vIcmsSt;
	
	String cstIpi;
	float pIpi;
	float vIpi;
	
	String cstPis;
	float pPis;
	float vPis;
	
	String cstCofins;
	float pCofins;
	float vCofins;
	
	
	
	
	
	

}
