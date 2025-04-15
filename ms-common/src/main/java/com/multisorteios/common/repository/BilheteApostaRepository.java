package com.multisorteios.common.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.multisorteios.common.model.BilheteAposta;

public interface BilheteApostaRepository extends JpaRepository<BilheteAposta, String>{

	@Query(value = "SELECT BA.* FROM BILHETE_APOSTA BA LEFT JOIN BILHETE B ON B.ID = BA.BILHETE_ID WHERE B.EVENTO_ID = :eventoId AND B.EMPRESA_ID = :empresaId ORDER BY B.DATA_BILHETE", nativeQuery = true)
	List<BilheteAposta> findByEventoEmpresaId(String eventoId, Integer empresaId);
	
}
