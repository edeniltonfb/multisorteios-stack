package com.multisorteios.cambista.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multisorteios.cambista.model.TokenAcessoCambista;
import com.multisorteios.cambista.trasnfer.ApostaBolaoTO;
import com.multisorteios.cambista.trasnfer.BilheteReportTO;
import com.multisorteios.cambista.trasnfer.ExtratoVendaTO;
import com.multisorteios.cambista.trasnfer.ItemExtratoVendaTO;
import com.multisorteios.common.enums.BilheteSituacao;
import com.multisorteios.common.exception.BusinessException;
import com.multisorteios.common.model.Administrador;
import com.multisorteios.common.model.ApostaBolao;
import com.multisorteios.common.model.Bilhete;
import com.multisorteios.common.model.Cambista;
import com.multisorteios.common.model.Cliente;
import com.multisorteios.common.model.Empresa;
import com.multisorteios.common.model.EntityProfileVO;
import com.multisorteios.common.model.Evento;
import com.multisorteios.common.model.Rota;
import com.multisorteios.common.model.fake.DadosAposta;
import com.multisorteios.common.service.AdministradorService;
import com.multisorteios.common.service.ApostaBolaoService;
import com.multisorteios.common.service.BilheteApostaService;
import com.multisorteios.common.service.BilheteService;
import com.multisorteios.common.service.CambistaService;
import com.multisorteios.common.service.ClienteService;
import com.multisorteios.common.service.EmpresaService;
import com.multisorteios.common.service.EventoService;
import com.multisorteios.common.service.MensagemWhatsAppService;
import com.multisorteios.common.service.RotaService;
import com.multisorteios.common.transfer.DadosClienteTO;
import com.multisorteios.common.transfer.EventoBasicoDTO;
import com.multisorteios.common.transfer.LoginTO;
import com.multisorteios.common.util.DateTime;
import com.multisorteios.common.util.IntegerUtils;
import com.multisorteios.common.util.StringUtils;

@Service
public class ApiService {

	@Autowired
	private TokenAcessoCambistaService tokenAcessoCambistaService;

	@Autowired
	private CambistaService cambistaService;
	
	@Autowired
	private RotaService rotaService;
	
	@Autowired
	private AdministradorService administradorService;

	@Autowired
	private EventoService eventoService;
	
	@Autowired
	private EmpresaService empresaService;

	@Autowired
	private BilheteService bilheteService;

	@Autowired 
	private CustomBilheteService customBilheteService;

	@Autowired
	private BilheteApostaService bilheteApostaService;

	@Autowired
	private ApostaBolaoService apostaBolaoService;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private BilheteReportService bilheteReportService;
	
	@Autowired
	private MensagemWhatsAppService mensagemWhatsAppService;

	public LoginTO login(Integer empresaId, LoginTO login) {
		Cambista cambista = cambistaService.findByEmpresaLogin(empresaId, login.getLogin());
		if(cambista != null && StringUtils.equals(login.getPassword(), cambista.getSenha())) {
			TokenAcessoCambista tac = tokenAcessoCambistaService.gerarCodigoAcesso(empresaId, cambista.getId());
			login.setToken(tac.getToken());
			login.setName(cambista.getNome());
			login.setProfile("CAMBISTA");
			return login;
		}
		
		Rota rota = rotaService.findByEmpresaLogin(empresaId, login.getLogin());
		if(rota != null && StringUtils.equals(login.getPassword(), rota.getSenha())) {
			TokenAcessoCambista tac = tokenAcessoCambistaService.gerarCodigoAcesso(empresaId, rota.getId());
			login.setToken(tac.getToken());
			login.setName(rota.getNome());
			login.setProfile("SUPERVISOR");
			
			return login;
		}
		
		Administrador administrador= administradorService.findByEmpresaLogin(empresaId, login.getLogin());
		if(administrador != null && StringUtils.equals(login.getPassword(), administrador.getSenha())) {
			TokenAcessoCambista tac = tokenAcessoCambistaService.gerarCodigoAcesso(empresaId, administrador.getId());
			login.setToken(tac.getToken());
			login.setName(administrador.getNome());
			login.setProfile("ADMIN");
			
			return login;
		}
		
		throw new BusinessException("Credenciais inválidas");
		
	}
	
	
	public <T extends EntityProfileVO> T validateToken(String token, Integer empresaId, Class<T> type) {
		EntityProfileVO entity = validateToken(token, empresaId);
		if (!type.isInstance(entity)) {
	        throw new BusinessException("Perfil inválido: esperado " + type.getSimpleName() 
	            + " mas encontrado " + entity.getClass().getSimpleName());
	    }
	    
	    return type.cast(entity);
		
	}
	
	public EntityProfileVO validateToken(String token, Integer empresaId) {
		TokenAcessoCambista tokenAcesso = tokenAcessoCambistaService.validateToken(empresaId, token);
		if(tokenAcesso == null) {
			throw new BusinessException("Token inválido");
		}
		
		Cambista cambista = cambistaService.find(tokenAcesso.getId().getCambistaId());
		if(cambista != null) {
			if(!"S".equals(cambista.getAtiva())) {
				throw new BusinessException("Cambista inativo");	
			}	
			if(!IntegerUtils.equals(empresaId, cambista.getEmpresaId())) {
				throw new BusinessException("Cambista inválido");	
			}
			
			return cambista;
		}
		
		Rota rota = rotaService.find(tokenAcesso.getId().getCambistaId());
		if(rota != null) {
			if(!"S".equals(rota.getAtiva())) {
				throw new BusinessException("Rota inativa");	
			}	
			if(!IntegerUtils.equals(empresaId, rota.getEmpresaId())) {
				throw new BusinessException("Rota inválida");	
			}
			
			return rota;
		}
		
		Administrador admin = administradorService.find(tokenAcesso.getId().getCambistaId());
		if(admin != null) {
			if(!"S".equals(admin.getAtiva())) {
				throw new BusinessException("Administrador inativo");	
			}	
			if(!IntegerUtils.equals(empresaId, admin.getEmpresaId())) {
				throw new BusinessException("Administrador inválido");	
			}
			
			return admin;
		}

		throw new BusinessException("Usuário indefinido");
	}

	public List<EventoBasicoDTO> listarEventos(Integer empresaId) {
		System.out.println("2");
		List<EventoBasicoDTO> eventoList = eventoService.listarEventos(empresaId);
		System.out.println("3");
		return eventoList;
	}

	public void alterarSenha(String token, Integer empresaId, String senhaAtual, String novaSenha) {
		
		EntityProfileVO entityProfile = validateToken(token, empresaId);
		
		if (entityProfile instanceof Cambista) {
			Cambista cambista = (Cambista) entityProfile;
			if(!StringUtils.equals(senhaAtual, cambista.getSenha())) {
				throw new BusinessException("Senha atual não confere");
			}

			cambista.setSenha(novaSenha);
			cambistaService.update(cambista);
			return;
		}
		
		if (entityProfile instanceof Rota) {
			Rota rota = (Rota) entityProfile;
			if(!StringUtils.equals(senhaAtual, rota.getSenha())) {
				throw new BusinessException("Senha atual não confere");
			}

			rota.setSenha(novaSenha);
			rotaService.update(rota);
			return;
		}
		
		if (entityProfile instanceof Administrador) {
			Administrador admin = (Administrador) entityProfile;
			if(!StringUtils.equals(senhaAtual, admin.getSenha())) {
				throw new BusinessException("Senha atual não confere");
			}

			admin.setSenha(novaSenha);
			administradorService.update(admin);
			return;
		}

		throw new BusinessException("Cadastro inválido");
	}

	public void registrarAposta(String token, Integer empresaId, ApostaBolaoTO requestData) {
		Cambista cambista = validateToken(token, empresaId, Cambista.class);

		String eventoId = requestData.getEventoId();
		Evento evento = eventoService.find(eventoId);
		Empresa empresa = empresaService.find(empresaId);

		String usuarioId = requestData.getTelefone();

		int quantidade = requestData.getListaApostas().size();
		if(quantidade == 0) {
			throw new BusinessException("Nenhuma aposta identificada no bilhete");
		}

		DadosAposta dados = refinarDadosAposta(quantidade, evento, usuarioId, empresaId);
		BigDecimal valorBilhete = dados.getValorBilhete();
		quantidade = dados.getQuantidadeApostas();

		Bilhete bilhete  = new Bilhete();
		bilhete.setValorBilhete(valorBilhete);
		bilhete.setUsuarioId(usuarioId);
		bilhete.setEmail("");
		bilhete.setNickname(requestData.getCidade());
		bilhete.setNomeApostador(requestData.getNome());
		bilhete.setEmpresaId(empresaId);
		bilhete.setCambistaId(cambista.getId());
		bilhete.setQrCodePix("");
		bilhete.setTxId("");
		bilhete.setDataBilhete(new Date());
		bilhete.setEventoId(eventoId);
		bilhete.setSituacao(BilheteSituacao.VALIDA);
		bilhete.setId(StringUtils.generateStringId(10));
		bilhete.setBeneficiarioId(null);
		bilhete.setQuantidadeApostas(quantidade);
		bilhete.setApostasDefinidas("S");
		bilhete.setEmpresaAlias("");
		bilhete.setDataLimitePagamento(new Date());
		bilhete.setHistorico("S");

		bilhete = bilheteService.insert(bilhete);

		//Registra as apostas
		ApostaBolao aposta;
		List<ApostaBolao> apostaList = new ArrayList<>();
		for(String numero : requestData.getListaApostas()) {

			aposta = new ApostaBolao();
			aposta.setEventoId(eventoId);
			aposta.setNumero(numero);
			aposta.setBilheteId(bilhete.getId());
			aposta.setAtualizacaoDataHora(new Date());
			aposta.setChave(StringUtils.generateStringId(10));
			apostaList.add(aposta);

		}
		apostaBolaoService.saveAll(apostaList);

		bilheteApostaService.insert2(bilhete.getId(), apostaList);
		
		
		BilheteReportTO report = new BilheteReportTO();
		report.setBilheteId(bilhete.getId());
		report.setCambista(cambista.getNome());
		report.setDataHora(new DateTime(bilhete.getDataHoraPagamento()).toString(DateTime.MASCARA_DATA_HORA_TELA));
		report.setDataSorteio(new DateTime(evento.getDataInicio()).toString(DateTime.MASCARA_DATA_HORA_TELA));
		report.setEmpresaNome(empresa.getNome());
		report.setNome(bilhete.getNomeApostador());
		report.setQuantidade(bilhete.getQuantidadeApostas());
		report.setValor(bilhete.getValorBilhete());
		report.setNumeros(apostaList.stream().map(x -> x.getNumero()).toList());
		
		String comprovante = bilheteReportService.gerarRelatorio(report, false);
		
		mensagemWhatsAppService.create(empresaId, cambista.getWhatsapp(), "Comprovante de pagamento", comprovante, false);
		mensagemWhatsAppService.create(empresaId, bilhete.getUsuarioId(), "Comprovante de pagamento", comprovante, false);

	}

	private DadosAposta refinarDadosAposta(Integer quantidade, Evento evento, String usuarioId, Integer empresaId) {
		Integer minimoPermitido = IntegerUtils.alternative(evento.getQuantidadeMinima(), 1);
		if(quantidade < minimoPermitido) {
			throw new BusinessException(String.format("Quantidade mínima permitida para essa rifa: %d", minimoPermitido));
		}

		Integer maximoPermitido = IntegerUtils.alternative(evento.getLimiteReservasPorUsuario(), 5);
		if(quantidade > maximoPermitido) {
			throw new BusinessException(String.format("Quantidade máxima permitida por compra para esta rifa: %d", maximoPermitido));
		}

		return eventoService.refinarDadosAposta(quantidade, evento, empresaId);

	}

	public ExtratoVendaTO extratoVendas(Integer empresaId, String token, String eventoId) {
		Cambista cambista = validateToken(token, empresaId, Cambista.class);
		List<Bilhete> bilheteList = customBilheteService.findByEventoCambistaId(eventoId, cambista.getId());
		BigDecimal valorTotal = bilheteList.stream().map(x -> x.getValorBilhete()).reduce(BigDecimal.ZERO, BigDecimal::add);
		BigDecimal comissao = valorTotal.multiply(cambista.getComissao()).divide(new BigDecimal(100), RoundingMode.CEILING);

		ExtratoVendaTO result = new ExtratoVendaTO();
		result.setComissao(comissao);
		result.setVendasRealizadas(bilheteList.size());
		result.setApostasRegistradas(bilheteList.stream().mapToInt(x -> x.getQuantidadeApostas()).sum());
		result.setArrecadacao(valorTotal);
		result.setValorLiquido(valorTotal.subtract(comissao));
		result.setApostas(
				bilheteList
				.stream()
				.map(x -> new ItemExtratoVendaTO(
						x.getNomeApostador(), 
						new DateTime(x.getDataBilhete()).toString(DateTime.MASCARA_DATA_HORA_TELA), 
						x.getQuantidadeApostas(), 
						x.getValorBilhete(),
						bilheteApostaService.obterListaApostaPorBilhete(x.getId())
						)
						).collect(Collectors.toList()));
		return result;
	}

	public DadosClienteTO obterDadosCliente(Integer empresaId, String token, String telefone) {

		validateToken(token, empresaId);

		Cliente cliente = clienteService.find(telefone, empresaId);
		DadosClienteTO result = new DadosClienteTO();
		if(cliente != null) {
			result.setCidade(cliente.getCidade());
			result.setEmail(cliente.getEmail());
			result.setNome(cliente.getNome());
			
		}
		result.setTelefone(telefone);

		return result;
	}
}
