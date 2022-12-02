package com.api.domain.TO;

import lombok.Data;

@Data
public class ProdutoTO {
	
	Long cProd;
	String xProd;
	String cEAN;
	String ncm;
	String cest;
	String cfop;
	String uCom;
	double qCom;
	float vProd;
	String cEANTrib;
	String uTrib;
	Double qTrib;
	float vDesc;
	float vOutro;
	
//	impostos
	
	float vTotTrib;
	
	String cstIcms;
	float pIcms;
	float vpIcms;
	
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
