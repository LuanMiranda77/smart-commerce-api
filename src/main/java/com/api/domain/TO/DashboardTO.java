package com.api.domain.TO;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class DashboardTO {
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date dataDeCriacao;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date dataFechamento;

}
