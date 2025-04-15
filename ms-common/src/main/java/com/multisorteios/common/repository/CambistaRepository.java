package com.multisorteios.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.multisorteios.common.model.Cambista;

@Repository
public interface CambistaRepository extends JpaRepository<Cambista, Integer>{

	@Query(value = "SELECT * FROM CAMBISTA WHERE EMPRESA_ID = :empresaId AND LOGIN = :login", nativeQuery = true)
	Cambista findByEmpresaLogin(Integer empresaId, String login);

}
