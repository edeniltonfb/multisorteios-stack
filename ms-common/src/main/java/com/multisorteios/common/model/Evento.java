package com.multisorteios.common.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import com.multisorteios.common.enums.EstrategiaNotificacao;
import com.multisorteios.common.enums.EventoSituacao;
import com.multisorteios.common.enums.EventoSubTipo;
import com.multisorteios.common.enums.EventoTipo;
import com.multisorteios.common.enums.Horario;

@Entity
@Table(name = "evento")
public class Evento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	private String id;

	@Column(name = "empresa_id", nullable = false)
	private Integer empresaId;

	@Column(name = "data_inicio", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataInicio;

	@Column(name = "horario_inicio", nullable = false, length = 3)
	private String horarioInicio;

	@Column(name = "situacao", nullable = false, length = 3)
	private String situacao;

	@Column(name = "tipo", nullable = false, length = 3)
	private String eventoTipo;

	@Column(name = "subtipo", nullable = false, length = 3)
	private String eventoSubtipo;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "horario_limite_venda", nullable = false)
	private Date horarioLimiteVenda;

	@Column(name = "expiracao_pix", nullable = false)
	private Integer expiracaoPix;

	@Column(name = "limite_reservas_por_usuario", nullable = false)
	private Integer limiteReservasPorUsuario;

	@Column(name = "email", nullable = true, length = 60)
	private String email;

	@Column(name = "prioridade", nullable = false)
	private Integer prioridade;

	@Column(name = "quantidade_minima", nullable = false, columnDefinition = "integer not null default 1")
	private Integer quantidadeMinima;

	@Column(name = "quantidade_gratuito", nullable = false, columnDefinition = "integer not null default 0")
	private Integer quantidadeGratuito;

	@Column(name = "incremento_minimo", nullable = false, columnDefinition = "integer not null default 1")
	private Integer incrementoMinimo;

	@Column(name = "evento_origem_id", nullable = true, length = 10)
	private String eventoOrigemId;

	@Column(name = "qtd_cotas_disponives", nullable = true)
	private Integer qtdCotasDisponives;

	@Column(name = "inicio_vendas", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date instanteInicioVendas;

	@Column(name = "mostrar_nome_pahpix", nullable = false, length = 1)
	private String mostrarNomePahpix;

	@Column(name = "permite_venda_filial", nullable = false, length = 1, columnDefinition = "char(1) not null default 'N'")
	private String permiteVendaFilial;

	@Column(name = "banco_empresa_id", nullable = true)
	private Integer bancoEmpresaId;

	@Column(name = "mostrar_dados_bancarios", nullable = true, length = 1)
	private String mostrarDadosBancarios;

	@Column(name = "estrategia_notificacao_matriz", nullable = false, length = 1, columnDefinition = "char(1) not null default 'P'")
	private String estrategiaNotificacaoMatriz;

	@Column(name = "estrategia_notificacao_filial", nullable = false, length = 1, columnDefinition = "char(1) not null default 'V'")
	private String estrategiaNotificacaoFilial;

	@Column(name = "mensagem_adicional_compra", nullable = true, length = 100)
	private String mensagemAdicionalCompra;

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

	public void setEmpresaId(Integer empresaId) {
		this.empresaId = empresaId;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Horario getHorarioInicio() {
		return Horario.parse(horarioInicio);
	}

	public void setHorarioInicio(Horario horarioInicio) {
		this.horarioInicio = Horario.getValue(horarioInicio);
	}

	public EventoSituacao getSituacao() {
		return EventoSituacao.parse(situacao);
	}

	public void setSituacao(EventoSituacao situacao) {
		this.situacao = EventoSituacao.getValue(situacao);
	}

	public EventoTipo getEventoTipo() {
		return EventoTipo.parse(eventoTipo);
	}

	public void setEventoTipo(EventoTipo eventoTipo) {
		this.eventoTipo = EventoTipo.getValue(eventoTipo);
	}

	public EventoSubTipo getEventoSubtipo() {
		return EventoSubTipo.parse(eventoSubtipo);
	}

	public void setEventoSubtipo(EventoSubTipo eventoSubtipo) {
		this.eventoSubtipo = EventoSubTipo.getValue(eventoSubtipo);
	}

	public Date getHorarioLimiteVenda() {
		return horarioLimiteVenda;
	}

	public void setHorarioLimiteVenda(Date horarioLimiteVenda) {
		this.horarioLimiteVenda = horarioLimiteVenda;
	}

	public Integer getExpiracaoPix() {
		return expiracaoPix;
	}

	public void setExpiracaoPix(Integer expiracaoPix) {
		this.expiracaoPix = expiracaoPix;
	}

	public Integer getLimiteReservasPorUsuario() {
		return limiteReservasPorUsuario;
	}

	public void setLimiteReservasPorUsuario(Integer limiteReservasPorUsuario) {
		this.limiteReservasPorUsuario = limiteReservasPorUsuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(Integer prioridade) {
		this.prioridade = prioridade;
	}

	public Integer getQuantidadeMinima() {
		return quantidadeMinima;
	}

	public void setQuantidadeMinima(Integer quantidadeMinima) {
		this.quantidadeMinima = quantidadeMinima;
	}

	public Integer getQuantidadeGratuito() {
		return quantidadeGratuito;
	}

	public void setQuantidadeGratuito(Integer quantidadeGratuito) {
		this.quantidadeGratuito = quantidadeGratuito;
	}

	public Integer getIncrementoMinimo() {
		return incrementoMinimo;
	}

	public void setIncrementoMinimo(Integer incrementoMinimo) {
		this.incrementoMinimo = incrementoMinimo;
	}

	public Date getInstanteInicioVendas() {
		return instanteInicioVendas;
	}

	public void setInstanteInicioVendas(Date instanteInicioVendas) {
		this.instanteInicioVendas = instanteInicioVendas;
	}

	public Date getAtualizacaoDataHora() {
		return atualizacaoDataHora;
	}

	public void setAtualizacaoDataHora(Date atualizacaoDataHora) {
		this.atualizacaoDataHora = atualizacaoDataHora;
	}

	public String getEventoOrigemId() {
		return eventoOrigemId;
	}

	public void setEventoOrigemId(String eventoOrigemId) {
		this.eventoOrigemId = eventoOrigemId;
	}

	public Integer getQtdCotasDisponives() {
		return qtdCotasDisponives;
	}

	public void setQtdCotasDisponives(Integer qtdCotasDisponives) {
		this.qtdCotasDisponives = qtdCotasDisponives;
	}

	public String getMostrarNomePahpix() {
		return mostrarNomePahpix;
	}

	public void setMostrarNomePahpix(String mostrarNomePahpix) {
		this.mostrarNomePahpix = mostrarNomePahpix;
	}

	public String getPermiteVendaFilial() {
		return permiteVendaFilial;
	}

	public void setPermiteVendaFilial(String permiteVendaFilial) {
		this.permiteVendaFilial = permiteVendaFilial;
	}

	public Integer getBancoEmpresaId() {
		return bancoEmpresaId;
	}

	public void setBancoEmpresaId(Integer bancoEmpresaId) {
		this.bancoEmpresaId = bancoEmpresaId;
	}

	public String getMostrarDadosBancarios() {
		return mostrarDadosBancarios;
	}

	public void setMostrarDadosBancarios(String mostrarDadosBancarios) {
		this.mostrarDadosBancarios = mostrarDadosBancarios;
	}

	public EstrategiaNotificacao getEstrategiaNotificacaoMatriz() {
		return EstrategiaNotificacao.parse(estrategiaNotificacaoMatriz);
	}

	public void setEstrategiaNotificacaoMatriz(EstrategiaNotificacao estrategiaNotificacaoMatriz) {
		this.estrategiaNotificacaoMatriz = EstrategiaNotificacao.getValue(estrategiaNotificacaoMatriz);
	}

	public EstrategiaNotificacao getEstrategiaNotificacaoFilial() {
		return EstrategiaNotificacao.parse(estrategiaNotificacaoFilial);
	}

	public void setEstrategiaNotificacaoFilial(EstrategiaNotificacao estrategiaNotificacaoFilial) {
		this.estrategiaNotificacaoFilial = EstrategiaNotificacao.getValue(estrategiaNotificacaoFilial);
	}

	public String getMensagemAdicionalCompra() {
		return mensagemAdicionalCompra;
	}

	public void setMensagemAdicionalCompra(String mensagemAdicionalCompra) {
		this.mensagemAdicionalCompra = mensagemAdicionalCompra;
	}

}
