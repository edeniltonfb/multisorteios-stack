package com.multisorteios.common.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multisorteios.common.exception.ObjectNotFoundException;
import com.multisorteios.common.model.Administrador;
import com.multisorteios.common.repository.AdministradorRepository;

@Service
public class AdministradorService {

	@Autowired 
	private AdministradorRepository repo;
	
	public Administrador find(Integer id) {
		if(id == null) {
			return null;
		}
		Optional<Administrador> obj = repo.findById(id);
		if(obj == null || !obj.isPresent()) {
			throw new ObjectNotFoundException(
					String.format("Objeto não encontrado. Id: %s; Tipo: %s", id, Administrador.class.getName()));
		}

		return obj.orElse(null);
	}
	
	public Administrador get(Integer id) {
		if(id == null) {
			return null;
		}
		Optional<Administrador> obj = repo.findById(id);
		return obj.orElse(null);
	}
	
	public List<Administrador> findAll() {
		return repo.findAll();
	}
	
	public String getName(Integer administradorId) {
		Administrador administrador = find(administradorId);
		if(administrador == null) {
			return null;
		}
		return administrador.getNome();
	}

	public Administrador findByEmpresaLogin(Integer empresaId, String login) {
		return repo.findByEmpresaLogin(empresaId, login);
	}

	public Administrador update(Administrador administrador) {
		return repo.save(administrador);
	}

}
