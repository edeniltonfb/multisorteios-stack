package com.multisorteios.common.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multisorteios.common.model.PrecoEvento;
import com.multisorteios.common.repository.PrecoEventoRepository;
import com.multisorteios.common.util.BigDecimalUtils;

@Service
public class PrecoEventoService {

	@Autowired
	private PrecoEventoRepository repo;

	public PrecoEvento create(String eventoId, Integer empresaId, Integer quantidade, BigDecimal valor) {
		PrecoEvento pe = new PrecoEvento();
		pe.getId().setEmpresaId(empresaId);
		pe.getId().setEventoId(eventoId);
		pe.getId().setQuantidade(quantidade);
		pe.setValor(valor);

		return repo.save(pe);
	}

	public List<PrecoEvento> findByEventoEmpresaId(String eventoId, Integer empresaId) {
		return repo.findByEventoEmpresaId(eventoId, empresaId);
	}

	public PrecoEvento findByEventoEmpresaId(String eventoId, Integer empresaId, Integer quantidade) {
		return repo.findByEventoEmpresaId(eventoId, empresaId, quantidade);
	}

	public BigDecimal calcularValorBilhete(String eventoId, Integer empresaId, int quantidade) {
		List<PrecoEvento> precoList = findByEventoEmpresaId(eventoId, empresaId);
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

		return valorBilhete;

	}

}