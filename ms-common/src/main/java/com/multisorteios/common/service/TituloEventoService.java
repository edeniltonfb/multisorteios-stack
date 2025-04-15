package com.multisorteios.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multisorteios.common.model.TituloEvento;
import com.multisorteios.common.repository.TituloEventoRepository;

@Service
public class TituloEventoService {

	@Autowired
	private TituloEventoRepository repo;

	public TituloEvento findByEventoEmpresaId(String eventoId, Integer empresaId) {

		return repo.findByEventoEmpresaId(eventoId, empresaId);
	}
	
}