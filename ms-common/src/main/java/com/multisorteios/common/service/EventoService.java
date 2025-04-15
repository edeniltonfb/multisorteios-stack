package com.multisorteios.common.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multisorteios.common.enums.EventoSituacao;
import com.multisorteios.common.enums.EventoSubTipo;
import com.multisorteios.common.enums.Horario;
import com.multisorteios.common.exception.ObjectNotFoundException;
import com.multisorteios.common.model.Empresa;
import com.multisorteios.common.model.Evento;
import com.multisorteios.common.model.ImagemEvento;
import com.multisorteios.common.model.PrecoEvento;
import com.multisorteios.common.model.TituloEvento;
import com.multisorteios.common.model.UrlPath;
import com.multisorteios.common.model.fake.DadosAposta;
import com.multisorteios.common.repository.EventoRepository;
import com.multisorteios.common.transfer.EventoBasicoDTO;
import com.multisorteios.common.util.BigDecimalUtils;
import com.multisorteios.common.util.CollectionsUtils;
import com.multisorteios.common.util.DateTime;

@Service
public class EventoService {
	
	@Autowired
	private EventoRepository repo;
	
	@Autowired
	private UrlPathService urlPathService;
	
	@Autowired
	private EmpresaService empresaService;
	
	@Autowired
	private PrecoEventoService precoEventoService;
	
	@Autowired
	private ImagemEventoService imagemEventoService;

	@Autowired
	private TituloEventoService tituloEventoService;
	
	public Evento find(String id) {
		Optional<Evento> obj = repo.findById(id);
		if(obj == null || !obj.isPresent()) {
			UrlPath url = urlPathService.findById(id);
			if(url != null) {
				return find(url.getEventoId());
			}else {
				throw new ObjectNotFoundException(
						String.format("Objeto não encontrado. Id: %s; Tipo: %s", id, Evento.class.getName()));
			}
		}

		return obj.orElse(null);
	}
	
	public List<EventoBasicoDTO> listarEventos(Integer empresaId) {
		List<Evento> eventoList = repo.findByEmpresaId(empresaId);
		Collections.sort(eventoList, new Comparator<Evento>() {

			@Override
			public int compare(Evento e1, Evento e2) {
				return e2.getDataInicio().compareTo(e1.getDataInicio());
			}

		});

		return eventoList
				.stream()
				.filter(x -> EventoSituacao.CANCELADO == x.getSituacao())
				.filter(x -> x.getDataInicio().after(DateTime.now().getDate().addDays(-40).getTime()))
				.map(x -> parseToBasicDTO(x))
				.collect(Collectors.toList());
	}
	
	private EventoBasicoDTO parseToBasicDTO(Evento evento) {
		TituloEvento te = tituloEventoService.findByEventoEmpresaId(evento.getId(), evento.getEmpresaId());

		EventoBasicoDTO dto = new EventoBasicoDTO();
		dto.setId(evento.getId());
		dto.setHorario(Horario.getDescription(evento.getHorarioInicio()));
		dto.setDataSorteio(evento.getDataInicio());
		dto.setTitulo(te == null? null : te.getTitulo());

		List<ImagemEvento> ieList = imagemEventoService.findByEventoEmpresaId(evento.getId(), evento.getEmpresaId());

		if(!CollectionsUtils.isNullOrEmpty(ieList)) {
			dto.setImageUrl("https://multisorteios.dev/restapi/files/download?fileName=" + ieList.get(0).getFileName());
		}

		dto.setSituacao(EventoSituacao.getValue(evento.getSituacao()));
		dto.setSubtipo(EventoSubTipo.getDescription(evento.getEventoSubtipo()));

		return dto;
	}

	public DadosAposta refinarDadosAposta(Integer quantidade, Evento evento, Integer empresaId) {

		Empresa empresa = empresaService.find(empresaId);
		//área de cálculo de desconto promocionais (inicio)
		List<PrecoEvento> precoList = precoEventoService.findByEventoEmpresaId(evento.getId(), empresa.getId());
		precoList = precoList.stream().sorted(new Comparator<PrecoEvento>() {
			@Override
			public int compare(PrecoEvento o1, PrecoEvento o2) {
				return o2.getId().getQuantidade().compareTo(o1.getId().getQuantidade());
			}
		}).collect(Collectors.toList());

		PrecoEvento precoEvento = precoList.stream().filter(x -> x.getId().getQuantidade() == 1).findFirst().orElse(null);
		BigDecimal valorAposta = precoEvento.getValor();

		for(PrecoEvento preco : precoList) {

			if(quantidade >= preco.getId().getQuantidade() && BigDecimalUtils.isGreaterThan(valorAposta, preco.getValor().divide(new BigDecimal(preco.getId().getQuantidade()), RoundingMode.HALF_DOWN ))) {
				valorAposta = preco.getValor().divide(new BigDecimal(preco.getId().getQuantidade()), RoundingMode.HALF_DOWN);
				break;
			}
		}

		BigDecimal valorBilhete = valorAposta.multiply(new BigDecimal(quantidade));
		for(PrecoEvento promocao : precoList) {
			if(quantidade <= promocao.getId().getQuantidade() && !BigDecimalUtils.isLessThan(valorBilhete, promocao.getValor())) {
				quantidade = promocao.getId().getQuantidade();
				valorBilhete = promocao.getValor();
				valorAposta = promocao.getValor().divide(new BigDecimal(promocao.getId().getQuantidade()), RoundingMode.HALF_DOWN);
			}else if(quantidade > promocao.getId().getQuantidade()) {
				BigDecimal valorBilheteTMP = new BigDecimal(quantidade/promocao.getId().getQuantidade()).multiply(promocao.getValor()).add(new BigDecimal(quantidade % promocao.getId().getQuantidade()).multiply(valorAposta));
				if(BigDecimalUtils.isLessThan(valorBilheteTMP, valorBilhete)) {
					valorBilhete = valorBilheteTMP;
				}
			}
		}

		return new DadosAposta(valorAposta, valorBilhete, quantidade) ;
	}

}
