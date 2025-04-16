package com.multisorteios.common.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multisorteios.common.model.Cliente;
import com.multisorteios.common.repository.ClienteRepository;
import com.multisorteios.common.util.CollectionsUtils;
import com.multisorteios.common.util.IntegerUtils;
import com.multisorteios.common.util.StringUtils;

@Service
public class ClienteService {

	@Autowired 
	private ClienteRepository repo;
	
	public Cliente find(String usuarioId, Integer empresaId) {
		if(StringUtils.isNullOrEmpty(usuarioId) || IntegerUtils.isNullOrZero(empresaId)) {
			return null;
		}
		
		usuarioId = StringUtils.removeSigns(usuarioId);
		Optional<Cliente> obj = repo.findById(new Cliente.Id(empresaId, usuarioId));
		return obj.orElse(null);
	}
	
	public List<Cliente> findAll() {
		return repo.findAll();
	}

	public Cliente insert(Cliente selectedCliente) {
		return repo.save(selectedCliente);
	}

	public Cliente update(Cliente selectedCliente) {
		return repo.save(selectedCliente);
	}

	public void delete(Cliente selectedCliente) {
		repo.delete(selectedCliente);
	}

	public void removeAll(List<Cliente> selectedClientes) {
		repo.deleteAllInBatch(selectedClientes.stream().collect(Collectors.toList()));
		
	}

	public String buscarNomePorClienteId(String clienteId, Integer empresaId) {
		Optional<Cliente> obj = repo.findById(new Cliente.Id(empresaId, clienteId));
		Cliente cliente = obj.orElse(null);
		if(cliente == null) {
			List<Cliente> clienteList = repo.findByMatrizId(clienteId, empresaId);
			if(CollectionsUtils.isNullOrEmpty(clienteList)) {
				return "";	
			}
			//É esperado que não passe por este ponto
			cliente = clienteList.get(0);
		}
		return cliente.getNome();
	}
	
	public void atualizarCadastroBasico(Integer empresaId, String nome, String telefone, String cidade) {
		String usuarioId = StringUtils.getStringAlfanumerica(telefone);
		Cliente cliente = find(usuarioId, empresaId);
		if(cliente == null) {
			cliente = new Cliente();
			cliente.getId().setUsuarioId(usuarioId);
			cliente.getId().setEmpresaId(empresaId);
			
		}
		cliente.setCidade(cidade);
		cliente.setNome(nome);
		cliente = repo.save(cliente);
	}

}
