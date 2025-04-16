package com.multisorteios.common.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.multisorteios.common.model.ImagemEvento;

@Repository
public interface ImagemEventoRepository extends JpaRepository<ImagemEvento, Integer> {
	
	@Query(value ="SELECT * FROM IMAGEM_EVENTO WHERE EVENTO_ID = :eventoId AND EMPRESA_ID = :empresaId", nativeQuery = true)
	List<ImagemEvento> findByEventoEmpresaId(@Param("eventoId") String eventoId, @Param("empresaId") Integer empresaId);

}
