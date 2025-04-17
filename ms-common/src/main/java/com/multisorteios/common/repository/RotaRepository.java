package com.multisorteios.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.multisorteios.common.model.Rota;

@Repository
public interface RotaRepository extends JpaRepository<Rota, Integer>{

	@Query(value = "SELECT * FROM ROTA WHERE EMPRESA_ID = :empresaId AND LOGIN = :login", nativeQuery = true)
	Rota findByEmpresaLogin(@Param("empresaId") Integer empresaId, @Param("login")String login);

}
