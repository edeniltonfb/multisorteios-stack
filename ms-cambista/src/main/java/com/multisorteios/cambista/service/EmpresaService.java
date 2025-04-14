package com.multisorteios.cambista.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.multisorteios.common.repository.EmpresaRepository;

@Service
public class EmpresaService {

	@Autowired
	private EmpresaRepository repo;
	
	public String teste() {
		repo.count();
		return "ok " + repo.count();
	}
}
