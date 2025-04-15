package com.multisorteios.common.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multisorteios.common.model.ApostaBolao;
import com.multisorteios.common.model.BilheteAposta;
import com.multisorteios.common.repository.BilheteApostaRepository;

@Service
public class BilheteApostaService {
	@Autowired
	private BilheteApostaRepository repo;
	
	public BilheteAposta getById(String bilheteId) {
		return repo.findById(bilheteId).orElse(null);
	}

	public void insert(String bilheteId, List<String> numeroList) {
		BilheteAposta ba = new BilheteAposta();
		ba.setBilheteId(bilheteId);
		Collections.sort(numeroList);
		String apostas = numeroList.toString().replace("[", "").replace("]","");
		ba.setApostaList(apostas.getBytes());
		
		repo.save(ba);
	}
	
	public void insert2(String bilheteId, List<ApostaBolao> apostaList) {
		BilheteAposta ba = new BilheteAposta();
		ba.setBilheteId(bilheteId);
		List<String> list = apostaList.stream().map(x -> x.getNumero()).collect(Collectors.toList());
		Collections.sort(list);
		String apostas = list.toString().replace("[", "").replace("]","");
		ba.setApostaList(apostas.getBytes());
		
		repo.save(ba);
	}
	
	public List<BilheteAposta> findByEventoEmpresaId(String eventoId, Integer empresaId) {
		return repo.findByEventoEmpresaId(eventoId, empresaId);
	}

	public List<String> obterListaApostaPorBilhete(String id) {
		BilheteAposta ba = repo.findById(id).orElse(null);
		if(ba == null) {
			return null;
		}
		
		String apostas = new String(ba.getApostaList()).replace("[", "").replace("]", "");
		return Arrays.asList(apostas.split(","));
	}

}
