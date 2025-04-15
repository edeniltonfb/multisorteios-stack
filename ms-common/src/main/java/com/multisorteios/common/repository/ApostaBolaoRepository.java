package com.multisorteios.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.multisorteios.common.model.ApostaBolao;

@Repository
public interface ApostaBolaoRepository extends JpaRepository<ApostaBolao, Integer> {

		
}
