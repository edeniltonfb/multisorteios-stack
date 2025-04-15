package com.multisorteios.common.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.multisorteios.common.model.PrecoEvento;

@Repository
public interface PrecoEventoRepository extends JpaRepository<PrecoEvento, PrecoEvento.Id> {

	@Query(value = "SELECT * FROM PRECO_EVENTO WHERE EVENTO_ID = :eventoId AND EMPRESA_ID = :empresaId", nativeQuery = true)
	List<PrecoEvento> findByEventoEmpresaId(String eventoId, Integer empresaId);

	@Query(value = "SELECT * FROM PRECO_EVENTO WHERE EVENTO_ID = :eventoId AND EMPRESA_ID = :empresaId AND QUANTIDADE = :quantidade LIMIT 1", nativeQuery = true)
	PrecoEvento findByEventoEmpresaId(String eventoId, Integer empresaId, Integer quantidade);
	
}
