package com.multisorteios.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.multisorteios.common.model.TituloEvento;

@Repository
public interface TituloEventoRepository extends JpaRepository<TituloEvento, TituloEvento.Id> {
	
	@Query(value = "SELECT * FROM TITULO_EVENTO TE WHERE TE.EVENTO_ID = :eventoId AND TE.EMPRESA_ID = :empresaId LIMIT 1", nativeQuery = true)
	TituloEvento findByEventoEmpresaId(String eventoId, Integer empresaId);

}
