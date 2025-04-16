package com.multisorteios.common.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.multisorteios.common.model.Evento;

public interface EventoRepository extends JpaRepository<Evento, String>{

	@Query(value = "SELECT * FROM EVENTO WHERE EMPRESA_ID = :empresaId", nativeQuery = true)
	List<Evento> findByEmpresaId(@Param("empresaId") Integer empresaId);
}
