package com.multisorteios.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.multisorteios.common.model.TituloEvento;

@Repository
public interface TituloEventoRepository extends JpaRepository<TituloEvento, TituloEvento.Id> {
	
	@Query(value = "SELECT * FROM TITULO_EVENTO TE WHERE TE.EVENTO_ID = :eventoId AND TE.EMPRESA_ID = :empresaId LIMIT 1", nativeQuery = true)
	TituloEvento findByEventoEmpresaId(@Param("eventoId") String eventoId, @Param("empresaId") Integer empresaId);

}
