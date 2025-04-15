package com.multisorteios.cambista.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multisorteios.cambista.model.TokenAcessoCambista;
import com.multisorteios.cambista.repository.TokenAcessoCambistaRepository;
import com.multisorteios.common.util.DateTime;
import com.multisorteios.common.util.IntegerUtils;
import com.multisorteios.common.util.StringUtils;

@Service
public class TokenAcessoCambistaService {

	@Autowired 
	private TokenAcessoCambistaRepository repo;
	
	public TokenAcessoCambista find(Integer cambistaId, Integer empresaId) {
		if(IntegerUtils.isNullOrZero(cambistaId) || IntegerUtils.isNullOrZero(empresaId)) {
			return null;
		}
		
		Optional<TokenAcessoCambista> obj = repo.findById(new TokenAcessoCambista.Id(empresaId, cambistaId));
		return obj.orElse(null);
	}
	
	public TokenAcessoCambista findByToken(String token) {
		return repo.findByToken(token);
	}
	

	public TokenAcessoCambista validateToken(Integer empresaId, String token) {
		TokenAcessoCambista ta = findByToken(token);
		if(ta == null || DateTime.now().getDate().after(ta.getValidade()) || !IntegerUtils.equals(empresaId, ta.getId().getEmpresaId())) {
			return null;
		}
		return ta;
	}

	public TokenAcessoCambista gerarCodigoAcesso(Integer empresaId, Integer cambistaId) {
		TokenAcessoCambista ta = find(cambistaId, empresaId);
		if (ta == null || DateTime.now().getDate().after(ta.getValidade())) {
		    if (ta == null) {
		        // Se `ta` for nulo, cria um novo objeto.
		        ta = new TokenAcessoCambista();
		        ta.getId().setEmpresaId(empresaId);
		        ta.getId().setCambistaId(cambistaId);
		    }
		}   
		// Atualiza os atributos de validade e código.
		ta.setValidade(new Date());
		// Atualiza o token
		ta.setToken(StringUtils.generateStringId(20).toLowerCase());

		// Salva o objeto `ta` no repositório
		return repo.save(ta);
	}
	
	
	
	/*public TokenAcesso insert(TokenAcesso item) {
		return repo.save(item);
	}

	public TokenAcesso update(TokenAcesso item) {
		return repo.save(item);
	}*/

}
