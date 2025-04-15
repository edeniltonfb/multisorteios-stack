package com.multisorteios.common.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.multisorteios.common.model.ImagemEvento;

@Repository
public interface ImagemEventoRepository extends JpaRepository<ImagemEvento, Integer> {
	
	@Query("SELECT ie FROM ImagemEvento ie WHERE ie.eventoId = ?1 and ie.empresaId = ?2")
	List<ImagemEvento> findByEventoEmpresaId(String eventoId, Integer empresaId);

}
