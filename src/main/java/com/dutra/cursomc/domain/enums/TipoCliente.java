package com.dutra.cursomc.domain.enums;

public enum TipoCliente {
	
	PESSOA_FISICA(1, "Pessoa Física"),
	PESSOA_JURIDICA(2, "Pessoa Jurídica");
	
	private Integer codigo;
	private String descricao;
	
	private TipoCliente(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static TipoCliente toEnum(Integer codigo) {
		if (codigo != null) {
			for (TipoCliente cliente : TipoCliente.values()) {
				if (codigo.equals(cliente.getCodigo())) {
					return cliente;
				}
			}
			throw new IllegalArgumentException("Id inválido: " + codigo);
		}
		return null;
	}
	
}