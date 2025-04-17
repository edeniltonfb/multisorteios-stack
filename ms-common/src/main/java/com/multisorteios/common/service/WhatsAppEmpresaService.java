package com.multisorteios.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multisorteios.common.model.WhatsAppEmpresa;
import com.multisorteios.common.repository.WhatsAppEmpresaRepository;

@Service
public class WhatsAppEmpresaService {

	@Autowired
	private WhatsAppEmpresaRepository repo;
	
	
	public WhatsAppEmpresa findByEmpresaId(Integer empresaId) {
		List<WhatsAppEmpresa> list = repo.findByEmpresaId(empresaId);
		if(list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

}
