package com.multisorteios.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.multisorteios.common.model.UrlPath;

@Repository
public interface UrlPathRepository extends JpaRepository<UrlPath, String>{

	
	@Modifying
	@Query(value = "DELETE FROM URL_PATH WHERE EVENTO_ID = :eventoId", nativeQuery = true)
	void deleteByEventoId(@Param("eventoId") String eventoId);
	
	@Query(value = "SELECT * FROM URL_PATH WHERE EVENTO_ID = :eventoId AND EMPRESA_ID = :empresaId", nativeQuery = true)
	UrlPath findByEventoEmpresaId(@Param("eventoId") String eventoId, @Param("empresaId") Integer empresaId);

}
