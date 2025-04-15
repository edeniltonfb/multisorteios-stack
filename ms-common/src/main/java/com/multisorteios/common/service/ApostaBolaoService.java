package com.multisorteios.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multisorteios.common.model.ApostaBolao;
import com.multisorteios.common.repository.ApostaBolaoRepository;

@Service
public class ApostaBolaoService {

	@Autowired
	private ApostaBolaoRepository repo;
	
	public List<ApostaBolao> saveAll(List<ApostaBolao> apostaList) {
		return repo.saveAll(apostaList);

	}
}
