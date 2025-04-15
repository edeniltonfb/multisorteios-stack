package com.multisorteios.common.model.fake;

import java.math.BigDecimal;

public class DadosAposta {
	private BigDecimal valorAposta;
	private BigDecimal valorBilhete;
	private Integer quantidadeApostas;

	public DadosAposta(BigDecimal valorAposta, BigDecimal valorBilhete, Integer quantidadeApostas) {
		super();
		this.valorAposta = valorAposta;
		this.valorBilhete = valorBilhete;
		this.quantidadeApostas = quantidadeApostas;
	}

	public BigDecimal getValorAposta() {
		return valorAposta;
	}

	public BigDecimal getValorBilhete() {
		return valorBilhete;
	}

	public Integer getQuantidadeApostas() {
		return quantidadeApostas;
	}
}
