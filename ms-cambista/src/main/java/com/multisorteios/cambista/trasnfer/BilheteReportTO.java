package com.multisorteios.cambista.trasnfer;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class BilheteReportTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nome;
	private String dataHora;
	private Integer quantidade;
	private BigDecimal valor;
	private String bilheteId;
	private String empresaNome;
	private String cambista;
	private String dataSorteio;
	private List<String> numeros;
	

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

	public String getBilheteId() {
		return bilheteId;
	}

	public void setBilheteId(String bilheteId) {
		this.bilheteId = bilheteId;
	}

	public String getEmpresaNome() {
		return empresaNome;
	}

	public void setEmpresaNome(String empresaNome) {
		this.empresaNome = empresaNome;
	}

	public String getCambista() {
		return cambista;
	}

	public void setCambista(String cambista) {
		this.cambista = cambista;
	}

	public String getDataSorteio() {
		return dataSorteio;
	}

	public void setDataSorteio(String dataSorteio) {
		this.dataSorteio = dataSorteio;
	}

	public List<String> getNumeros() {
		return numeros;
	}

	public void setNumeros(List<String> numeros) {
		this.numeros = numeros;
	}

}
