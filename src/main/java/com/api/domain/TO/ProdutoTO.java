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
	float porcIcms;
	float valorIcms;
	float porcIcmsSt;
	float valorIcmsSt;
	
	String cstIpi;
	float porcIpi;
	float valorIpi;
	
	String cstPis;
	float porcPis;
	float valorPis;
	
	String cstCofins;
	float porcCofins;
	float valorCofins;
	
	
	
	
	
	

}
