package com.multisorteios.common.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multisorteios.common.exception.ObjectNotFoundException;
import com.multisorteios.common.model.Empresa;
import com.multisorteios.common.repository.EmpresaRepository;

@Service
public class EmpresaService {

	@Autowired 
	private EmpresaRepository repo;
	
	public Empresa find(Integer id) {
		Optional<Empresa> obj = repo.findById(id);
		if(obj == null || !obj.isPresent()) {
			throw new ObjectNotFoundException(
					String.format("Objeto não encontrado. Id: %s; Tipo: %s", id, Empresa.class.getName()));
		}

		return obj.orElse(null);
	}

	
}
