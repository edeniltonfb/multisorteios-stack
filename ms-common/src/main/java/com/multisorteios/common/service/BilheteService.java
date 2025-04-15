package com.multisorteios.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multisorteios.common.model.Bilhete;
import com.multisorteios.common.repository.BilheteRepository;
import com.multisorteios.common.util.StringUtils;

@Service
public class BilheteService {
	
	@Autowired
	private BilheteRepository repo;

	public Bilhete insert(Bilhete obj) {
		if(StringUtils.isNullOrEmpty(obj.getReal())) {
			obj.setReal("S");
		}
		
		obj.setUsuarioId(StringUtils.removeSigns(obj.getUsuarioId()));
		
		return repo.save(obj);
	}
}
