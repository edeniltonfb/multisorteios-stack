package com.multisorteios.cambista.trasnfer;

import java.io.Serializable;
import java.util.List;

public class ApostaBolaoTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<String> listaApostas;
	private String eventoId;
	private String nome;
	private String telefone;
	private String cidade;

	public ApostaBolaoTO() {
		super();
	}

	public List<String> getListaApostas() {
		return listaApostas;
	}

	public void setListaApostas(List<String> listaApostas) {
		this.listaApostas = listaApostas;
	}

	public String getEventoId() {
		return eventoId;
	}

	public void setEventoId(String eventoId) {
		this.eventoId = eventoId;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	
}
