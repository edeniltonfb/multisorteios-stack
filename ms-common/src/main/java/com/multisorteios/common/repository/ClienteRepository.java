package com.multisorteios.common.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.multisorteios.common.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Cliente.Id>{

	@Query(value = "SELECT * FROM CLIENTE C WHERE C.USUARIO_ID = :clienteId AND C.EMPRESA_ID IN (SELECT E.ID FROM EMPRESA E WHERE E.MATRIZ_ID = :matrizId)", nativeQuery = true)
	List<Cliente> findByMatrizId(@Param("clienteId") String clienteId, @Param("matrizId") Integer matrizId);
	

}
