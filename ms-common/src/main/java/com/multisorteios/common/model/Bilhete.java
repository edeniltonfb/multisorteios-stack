package com.multisorteios.common.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import com.multisorteios.common.enums.BilheteSituacao;
import com.multisorteios.common.enums.FormaConfirmacaoPagamento;

@Entity
@Table(name = "bilhete")
public class Bilhete implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	private String id;

	@Column(name = "evento_id", nullable = false)
	private String eventoId;

	@Column(name = "empresa_id", nullable = false)
	private Integer empresaId;

	@Column(name = "filial_id", nullable = true)
	private Integer filialId;
	
	@Column(name = "cambista_id", nullable = true)
	private Integer cambistaId;

	@Column(name = "usuario_id", nullable = false, length = 14)
	private String usuarioId;

	@Column(name = "email", nullable = true, length = 120)
	private String email;

	@Column(name = "nome_apostador", nullable = false, length = 120)
	private String nomeApostador;

	@Column(name = "situacao", nullable = false, length = 3)
	private String situacao;

	@Column(name = "valor_bilhete", nullable = false, precision = 10, scale = 2)
	private BigDecimal valorBilhete;

	@Column(name = "qrcode_pix", nullable = false, length = 400)
	private String qrCodePix;

	@Column(name = "txid", nullable = false, length = 400)
	private String txId;

	@Column(name = "data_limite_pagamento", nullable = false, columnDefinition = "timestamp not null default current_timestamp")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataLimitePagamento;
	
	@Column(name = "data_hora_pagamento", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataHoraPagamento;
	
	@Column(name = "data_bilhete", nullable = false, columnDefinition = "timestamp not null default current_timestamp")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataBilhete;

	@Column(name = "vendedor_id", nullable = true)
	private Integer vendedorId;

	@Column(name = "cobrador_id", nullable = true)
	private Integer cobradorId;

	@Column(name = "comprovante_pagamento", nullable = true, length = 60)
	private String comprovantePagamento;

	@Column(name = "forma_confirmacao_pagamento", nullable = true, length = 2)
	private String formaConfirmacaoPagamento;

	@Column(name = "real", nullable = true, length = 1)
	private String real;

	@Column(name = "beneficiarioId", nullable = true, length = 14)
	private String beneficiarioId;

	@Column(name = "pareceiroId", nullable = true, length = 14)
	private String parceiroId;

	@Column(name = "empresa_alias", nullable = true, length = 32)
	private String empresaAlias;

	@Column(name = "quantidade_apostas", nullable = false, columnDefinition = "SMALLINT not null default 0")
	private int quantidadeApostas;

	@Column(name = "apostas_definidas", nullable = false, length = 1, columnDefinition = "char(1) not null default 'S'")
	private String apostasDefinidas;

	@Column(name = "historico", nullable = false, length = 1, columnDefinition = "char(1) not null default 'N'")
	private String historico;

	@Column(name = "banco_empresa_id", nullable = true)
	private Integer bancoEmpresaId;

	@Column(name = "nickname", nullable = true, length = 40)
	private String nickname;

	@Column(name = "atualizacao_data_hora", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@Version
	private Date atualizacaoDataHora;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getEmpresaId() {
		return empresaId;
	}

	public String getEventoId() {
		return eventoId;
	}

	public void setEventoId(String eventoId) {
		this.eventoId = eventoId;
	}

	public void setEmpresaId(Integer empresaId) {
		this.empresaId = empresaId;
	}

	public Integer getCambistaId() {
		return cambistaId;
	}

	public void setCambistaId(Integer cambistaId) {
		this.cambistaId = cambistaId;
	}

	public String getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(String usuarioId) {
		this.usuarioId = usuarioId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNomeApostador() {
		return nomeApostador;
	}

	public void setNomeApostador(String nomeApostador) {
		this.nomeApostador = nomeApostador;
	}

	public BilheteSituacao getSituacao() {
		return BilheteSituacao.parse(situacao);
	}

	public void setSituacao(BilheteSituacao situacao) {
		this.situacao = BilheteSituacao.getValue(situacao);
	}

	public Date getAtualizacaoDataHora() {
		return atualizacaoDataHora;
	}

	public void setAtualizacaoDataHora(Date atualizacaoDataHora) {
		this.atualizacaoDataHora = atualizacaoDataHora;
	}

	public BigDecimal getValorBilhete() {
		return valorBilhete;
	}

	public void setValorBilhete(BigDecimal valorBilhete) {
		this.valorBilhete = valorBilhete;
	}

	public String getQrCodePix() {
		return qrCodePix;
	}

	public void setQrCodePix(String qrCodePix) {
		this.qrCodePix = qrCodePix;
	}

	public String getTxId() {
		return txId;
	}

	public void setTxId(String txId) {
		this.txId = txId;
	}

	public Date getDataLimitePagamento() {
		return dataLimitePagamento;
	}

	public void setDataLimitePagamento(Date dataLimitePagamento) {
		this.dataLimitePagamento = dataLimitePagamento;
	}

	public Date getDataHoraPagamento() {
		return dataHoraPagamento;
	}

	public void setDataHoraPagamento(Date dataPagamento) {
		this.dataHoraPagamento = dataPagamento;
	}

	public Date getDataBilhete() {
		return dataBilhete;
	}

	public void setDataBilhete(Date dataBilhete) {
		this.dataBilhete = dataBilhete;
	}

	public Integer getVendedorId() {
		return vendedorId;
	}

	public void setVendedorId(Integer vendedorId) {
		this.vendedorId = vendedorId;
	}

	public Integer getCobradorId() {
		return cobradorId;
	}

	public void setCobradorId(Integer cobradorId) {
		this.cobradorId = cobradorId;
	}

	public String getComprovantePagamento() {
		return comprovantePagamento;
	}

	public void setComprovantePagamento(String comprovantePagamento) {
		this.comprovantePagamento = comprovantePagamento;
	}

	public String getFormaConfirmacaoPagamento() {
		return formaConfirmacaoPagamento;
	}

	public void setFormaConfirmacaoPagamento(FormaConfirmacaoPagamento formaConfirmacaoPagamento) {
		this.formaConfirmacaoPagamento = FormaConfirmacaoPagamento.getValue(formaConfirmacaoPagamento);
	}

	public String getReal() {
		return real;
	}

	public void setReal(String real) {
		this.real = real;
	}

	public String getBeneficiarioId() {
		return beneficiarioId;
	}

	public void setBeneficiarioId(String benefificarioId) {
		this.beneficiarioId = benefificarioId;
	}

	public String getParceiroId() {
		return parceiroId;
	}

	public void setParceiroId(String parceiroId) {
		this.parceiroId = parceiroId;
	}

	public int getQuantidadeApostas() {
		return quantidadeApostas;
	}

	public void setQuantidadeApostas(int quantidadeApostas) {
		this.quantidadeApostas = quantidadeApostas;
	}

	public String getEmpresaAlias() {
		return empresaAlias;
	}

	public void setEmpresaAlias(String empresaAlias) {
		this.empresaAlias = empresaAlias;
	}

	public String getApostasDefinidas() {
		return apostasDefinidas;
	}

	public void setApostasDefinidas(String apostasDefinidas) {
		this.apostasDefinidas = apostasDefinidas;
	}

	public String getHistorico() {
		return historico;
	}

	public void setHistorico(String historico) {
		this.historico = historico;
	}

	public Integer getBancoEmpresaId() {
		return bancoEmpresaId;
	}

	public void setBancoEmpresaId(Integer bancoEmpresaId) {
		this.bancoEmpresaId = bancoEmpresaId;
	}

	public Integer getFilialId() {
		return filialId;
	}

	public void setFilialId(Integer filialId) {
		this.filialId = filialId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

}
