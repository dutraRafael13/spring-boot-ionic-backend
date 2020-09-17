package com.dutra.cursomc.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.dutra.cursomc.domain.enums.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class PagamentoComBoleto extends Pagamento {

	private static final long serialVersionUID = 1L;

	@JsonFormat(pattern = "dd/MM/yyyy")
	@Column
	private Date dataVencimento;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	@Column
	private Date dataPagamento;
	
	public PagamentoComBoleto() {
		super();
		this.dataVencimento = new Date();
		this.dataPagamento = new Date();
	}
	
	public PagamentoComBoleto(Integer id, EstadoPagamento estado, Pedido pedido, Date dataVencimento,
			Date dataPagamento) {
		super(id, estado, pedido);
		this.dataVencimento = dataVencimento;
		this.dataPagamento = dataPagamento;
	}
	
	public Date getDataVencimento() {
		return dataVencimento;
	}
	
	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	
	public Date getDataPagamento() {
		return dataPagamento;
	}
	
	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	
}