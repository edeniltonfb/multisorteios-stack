package com.multisorteios.common.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multisorteios.common.exception.ObjectNotFoundException;
import com.multisorteios.common.model.Cambista;
import com.multisorteios.common.repository.CambistaRepository;

@Service
public class CambistaService {

	@Autowired 
	private CambistaRepository repo;
	
	public Cambista find(Integer id) {
		if(id == null) {
			return null;
		}
		Optional<Cambista> obj = repo.findById(id);
		if(obj == null || !obj.isPresent()) {
			throw new ObjectNotFoundException(
					String.format("Objeto não encontrado. Id: %s; Tipo: %s", id, Cambista.class.getName()));
		}

		return obj.orElse(null);
	}
	
	public Cambista get(Integer id) {
		if(id == null) {
			return null;
		}
		Optional<Cambista> obj = repo.findById(id);
		return obj.orElse(null);
	}
	
	public List<Cambista> findAll() {
		return repo.findAll();
	}
	
	public String getName(Integer cambistaId) {
		Cambista cambista = find(cambistaId);
		if(cambista == null) {
			return null;
		}
		return cambista.getNome();
	}

	public Cambista findByEmpresaLogin(Integer empresaId, String login) {
		return repo.findByEmpresaLogin(empresaId, login);
	}

	public Cambista update(Cambista cambista) {
		return repo.save(cambista);
	}

}
