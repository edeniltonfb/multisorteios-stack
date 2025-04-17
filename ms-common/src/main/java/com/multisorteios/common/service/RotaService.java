package com.multisorteios.common.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multisorteios.common.exception.ObjectNotFoundException;
import com.multisorteios.common.model.Rota;
import com.multisorteios.common.repository.RotaRepository;

@Service
public class RotaService {

	@Autowired 
	private RotaRepository repo;
	
	public Rota find(Integer id) {
		if(id == null) {
			return null;
		}
		Optional<Rota> obj = repo.findById(id);
		if(obj == null || !obj.isPresent()) {
			throw new ObjectNotFoundException(
					String.format("Objeto não encontrado. Id: %s; Tipo: %s", id, Rota.class.getName()));
		}

		return obj.orElse(null);
	}
	
	public Rota get(Integer id) {
		if(id == null) {
			return null;
		}
		Optional<Rota> obj = repo.findById(id);
		return obj.orElse(null);
	}
	
	public List<Rota> findAll() {
		return repo.findAll();
	}
	
	public String getName(Integer rotaId) {
		Rota rota = find(rotaId);
		if(rota == null) {
			return null;
		}
		return rota.getNome();
	}

	public Rota findByEmpresaLogin(Integer empresaId, String login) {
		return repo.findByEmpresaLogin(empresaId, login);
	}

	public Rota update(Rota rota) {
		return repo.save(rota);
	}

}
