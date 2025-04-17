package com.multisorteios.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.multisorteios.common.model.Administrador;

@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, Integer>{

	@Query(value = "SELECT * FROM ADMINISTRADOR WHERE EMPRESA_ID = :empresaId AND LOGIN = :login", nativeQuery = true)
	Administrador findByEmpresaLogin(@Param("empresaId") Integer empresaId, @Param("login")String login);

}
