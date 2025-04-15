package com.multisorteios.cambista.trasnfer;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class ItemExtratoVendaTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nome;
	private String dataHora;
	private Integer quantidade;
	private BigDecimal valor;
	private List<String> numeros;
	
	public ItemExtratoVendaTO(String nome, String dataHora, Integer quantidade, BigDecimal valor,
			List<String> numeros) {
		super();
		this.nome = nome;
		this.dataHora = dataHora;
		this.quantidade = quantidade;
		this.valor = valor;
		this.numeros = numeros;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDataHora() {
		return dataHora;
	}

	public void setDataHora(String dataHora) {
		this.dataHora = dataHora;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public List<String> getNumeros() {
		return numeros;
	}

	public void setNumeros(List<String> numeros) {
		this.numeros = numeros;
	}

}
