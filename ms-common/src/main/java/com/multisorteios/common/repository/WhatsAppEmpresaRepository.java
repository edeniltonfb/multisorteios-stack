package com.multisorteios.common.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.multisorteios.common.model.WhatsAppEmpresa;

@Repository
public interface WhatsAppEmpresaRepository extends JpaRepository<WhatsAppEmpresa, String> {

	List<WhatsAppEmpresa> findByEmpresaId(Integer empresaId);
}
