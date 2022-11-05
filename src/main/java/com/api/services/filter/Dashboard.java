package com.api.services.filter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import lombok.Data;


@Data

public class Dashboard implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Relatorio ticketMedio;
	private Relatorio totalPedidoRealizado;
	private Relatorio totalPedidoPago;
	private Relatorio totalPedidoCancelado;
	private BigDecimal totalGeralMes;
	private BigDecimal totalGeralMesPassado;
	private BigDecimal totalVarejo;
	private BigDecimal totalAtacado;
	private BigDecimal totalVarejoCredito;
	private BigDecimal totalVarejoBoleto;
	private BigDecimal totalAtacadoCredito;
	private BigDecimal totalAtacadoBoleto;
	private List<Number> arrayVendaPorHoras;
	private List<Categorias> arrayVendaPorCategorias;
	private List<Number> arrayFormasPagamentos;
	private List<Clientes> arrayTopClientes;
	private List<Number> arrayFaturamentoAnual;
	private List<Number> arrayFaturamentoMensal; 
    
	

}
