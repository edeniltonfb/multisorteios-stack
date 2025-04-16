package com.multisorteios.cambista.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.multisorteios.common.model.Bilhete;
import com.multisorteios.common.repository.BilheteRepository;

@Service
public class CustomBilheteService {

	@Autowired
	private BilheteRepository repo;
	
	public List<Bilhete> findByEventoCambistaId(String eventoId, Integer cambistaId){
		
		System.out.println("busca por evento " + eventoId + " cambista : " + cambistaId);
		
		Bilhete exemplo = new Bilhete();
		exemplo.setEventoId(eventoId);
		exemplo.setCambistaId(cambistaId);

		Example<Bilhete> example = Example.of(exemplo);
		
		// ordenando por dataBilhete, descendente
		Sort sort = Sort.by(Sort.Direction.DESC, "dataBilhete");

		return repo.findAll(example, sort);
	}
}
