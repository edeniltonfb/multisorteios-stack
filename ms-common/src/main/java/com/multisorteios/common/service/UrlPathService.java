package com.multisorteios.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multisorteios.common.model.UrlPath;
import com.multisorteios.common.repository.UrlPathRepository;

@Service
public class UrlPathService {

	@Autowired
	private UrlPathRepository repo;
	
	public UrlPath findById(String url) {
		return repo.findById(url).orElse(null);
	}
	
	public UrlPath create(String url, String eventoId, Integer empresaId) {
		UrlPath item = new UrlPath();
		item.setEmpresaId(empresaId);
		item.setEventoId(eventoId);
		item.setUrl(url);
		
		return repo.save(item);
	}

	public void deleteById(String eventoId) {
		repo.deleteByEventoId(eventoId);
		
	}
	
	public UrlPath findByEventoEmpresaId(String eventoId, Integer empresaId) {
		
		UrlPath result = repo.findByEventoEmpresaId(eventoId, empresaId);
		return result;
	}
}
