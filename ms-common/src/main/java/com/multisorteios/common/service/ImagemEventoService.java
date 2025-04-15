package com.multisorteios.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multisorteios.common.model.ImagemEvento;
import com.multisorteios.common.repository.ImagemEventoRepository;

@Service
public class ImagemEventoService {

	@Autowired
	private ImagemEventoRepository repo;

	public List<ImagemEvento> findByEventoEmpresaId(String eventoId, Integer empresaId) {

		return repo.findByEventoEmpresaId(eventoId, empresaId);
	}
	
	public ImagemEvento findOneByEventoEmpresaId(String eventoId, Integer empresaId) {

		List<ImagemEvento> list = repo.findByEventoEmpresaId(eventoId, empresaId);
		if(list.size() < 1) {
			return null;
		}
		
		return list.get(0);
	}
	
}