package br.com.multisorteios.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.multisorteios.common.model.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Integer>{

}
