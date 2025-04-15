package com.multisorteios.cambista.trasnfer;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class ExtratoVendaTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer vendasRealizadas;
	private Integer apostasRegistradas;
	private BigDecimal arrecadacao;
	private BigDecimal comissao;
	private BigDecimal valorLiquido;
	private List<ItemExtratoVendaTO> apostas;

	public Integer getVendasRealizadas() {
		return vendasRealizadas;
	}

	public void setVendasRealizadas(Integer vendasRealizadas) {
		this.vendasRealizadas = vendasRealizadas;
	}

	public Integer getApostasRegistradas() {
		return apostasRegistradas;
	}

	public void setApostasRegistradas(Integer apostasRegistradas) {
		this.apostasRegistradas = apostasRegistradas;
	}

	public BigDecimal getArrecadacao() {
		return arrecadacao;
	}

	public void setArrecadacao(BigDecimal arrecadacao) {
		this.arrecadacao = arrecadacao;
	}

	public BigDecimal getComissao() {
		return comissao;
	}

	public void setComissao(BigDecimal comissao) {
		this.comissao = comissao;
	}

	public BigDecimal getValorLiquido() {
		return valorLiquido;
	}

	public void setValorLiquido(BigDecimal valorLiquido) {
		this.valorLiquido = valorLiquido;
	}

	public List<ItemExtratoVendaTO> getApostas() {
		return apostas;
	}

	public void setApostas(List<ItemExtratoVendaTO> apostas) {
		this.apostas = apostas;
	}

}
