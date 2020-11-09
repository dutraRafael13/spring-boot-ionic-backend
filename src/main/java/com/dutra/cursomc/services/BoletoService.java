package com.dutra.cursomc.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.dutra.cursomc.domain.PagamentoComBoleto;

@Service
public class BoletoService {

	private static final int VENCIMENTO = 7;

	public void preenchePagamentoComBoleto(PagamentoComBoleto pagamento, Date instantePedido) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(instantePedido);
		calendar.add(Calendar.DAY_OF_MONTH, VENCIMENTO);
		pagamento.setDataVencimento(calendar.getTime());
	}
	
}