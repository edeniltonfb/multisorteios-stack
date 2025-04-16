package com.multisorteios.cambista.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.multisorteios.cambista.model.TokenAcessoCambista;

@Repository
public interface TokenAcessoCambistaRepository extends JpaRepository<TokenAcessoCambista, TokenAcessoCambista.Id>{

	@Query(value = "SELECT * FROM TOKEN_ACESSO_CAMBISTA WHERE TOKEN = :token", nativeQuery = true)
	TokenAcessoCambista findByToken(@Param("token") String token);

}
