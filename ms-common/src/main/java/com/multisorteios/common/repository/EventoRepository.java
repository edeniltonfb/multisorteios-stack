package com.multisorteios.common.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.multisorteios.common.model.Evento;

public interface EventoRepository extends JpaRepository<Evento, String>{

	List<Evento> findByEmpresaId(Integer empresaId);
}
