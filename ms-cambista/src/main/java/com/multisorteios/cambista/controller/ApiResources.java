package com.multisorteios.cambista.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.multisorteios.cambista.service.ApiService;
import com.multisorteios.cambista.trasnfer.ApostaBolaoTO;
import com.multisorteios.cambista.trasnfer.CambistaTO;
import com.multisorteios.cambista.trasnfer.ExtratoVendaTO;
import com.multisorteios.cambista.trasnfer.RotaTO;
import com.multisorteios.common.exception.BusinessException;
import com.multisorteios.common.model.Cambista;
import com.multisorteios.common.model.EntityProfileVO;
import com.multisorteios.common.model.Rota;
import com.multisorteios.common.transfer.DadosClienteTO;
import com.multisorteios.common.transfer.EmptyResponseTO;
import com.multisorteios.common.transfer.EventoBasicoDTO;
import com.multisorteios.common.transfer.GenericCollectionResponseTO;
import com.multisorteios.common.transfer.GenericResponseTO;
import com.multisorteios.common.transfer.LoginTO;
import com.multisorteios.common.util.Constants;

@RestController
@RequestMapping(value = "/api")
public class ApiResources {

	@Autowired
	private ApiService service;

	@PostMapping("/login")
	public ResponseEntity<GenericResponseTO<LoginTO>> login(@RequestHeader Integer empresaId, @RequestBody LoginTO login) {
		
		GenericResponseTO<LoginTO> result;
		try{
			LoginTO data = service.login(empresaId, login);
			result = new GenericResponseTO<LoginTO>(true, null, data);
		}catch (BusinessException e) {
			result = new GenericResponseTO<LoginTO>(false, e.getMessage(), null);
		}catch (Exception e) {
			e.printStackTrace();
			result = new GenericResponseTO<LoginTO>(false, Constants.DEFAULT_ERROR_MESSAGE, null);
		}
		
		return ResponseEntity.ok().body(result);
		
	}

	@GetMapping("/validatetoken")
	public ResponseEntity<EmptyResponseTO> validateToken(@RequestHeader Integer empresaId, @RequestParam String token) {
		
		EmptyResponseTO result;
		try{
			service.validateToken(token, empresaId);
			result = new EmptyResponseTO(true, null);
		}catch (BusinessException e) {
			result = new EmptyResponseTO(false, e.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			result = new EmptyResponseTO(false, Constants.DEFAULT_ERROR_MESSAGE);
		}
		
		return ResponseEntity.ok().body(result);
		
	}
	
	@PutMapping("/alterarsenha")
	public ResponseEntity<EmptyResponseTO> alterarSenha(@RequestHeader Integer empresaId, @RequestParam String token, @RequestParam String senhaAtual,  @RequestParam String novaSenha) {
		
		EmptyResponseTO result;
		try{
			service.alterarSenha(token, empresaId, senhaAtual, novaSenha);
			result = new EmptyResponseTO(true, null);
		}catch (BusinessException e) {
			result = new EmptyResponseTO(false, e.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			result = new EmptyResponseTO(false, Constants.DEFAULT_ERROR_MESSAGE);
		}
		
		return ResponseEntity.ok().body(result);
		
	}
	
	@PostMapping("/registraraposta")
	public ResponseEntity<EmptyResponseTO> registrarAposta(@RequestHeader Integer empresaId, @RequestParam String token, @RequestBody ApostaBolaoTO requestData) {
		
		EmptyResponseTO result;
		try{
			service.registrarAposta(token, empresaId, requestData);
			result = new EmptyResponseTO(true, null);
		}catch (BusinessException e) {
			result = new EmptyResponseTO(false, e.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			result = new EmptyResponseTO(false, Constants.DEFAULT_ERROR_MESSAGE);
		}
		
		return ResponseEntity.ok().body(result);
		
	}


	@GetMapping("/listarsorteios")
	public ResponseEntity<GenericCollectionResponseTO<EventoBasicoDTO, List<EventoBasicoDTO>>> listarEventos(@RequestHeader Integer empresaId, @RequestParam String token) {
		
		GenericCollectionResponseTO<EventoBasicoDTO, List<EventoBasicoDTO>> result;
		try{
			EntityProfileVO entity = service.validateToken(token, empresaId);
			List<EventoBasicoDTO> data = service.listarEventos(entity.getEmpresaId());
			result = new GenericCollectionResponseTO<EventoBasicoDTO, List<EventoBasicoDTO>>(true, null, data);
		}catch (BusinessException e) {
			result = new GenericCollectionResponseTO<EventoBasicoDTO, List<EventoBasicoDTO>>(false, e.getMessage(), null);
		}catch (Exception e) {
			e.printStackTrace();
			result = new GenericCollectionResponseTO<EventoBasicoDTO, List<EventoBasicoDTO>>(false, Constants.DEFAULT_ERROR_MESSAGE, null);
		}
		
		return ResponseEntity.ok().body(result);
		
	}
	
	/*@GetMapping("/detalharvendas")
	public ResponseEntity<DetalheVendaTO> detalharVendas(@RequestHeader Integer empresaId, @RequestParam String token, @RequestParam String eventoId) {
		return ResponseEntity.ok().body(service.detalharVendas(empresaId, token, eventoId));
	}*/
	
	@GetMapping("/extratovendas")
	public ResponseEntity<GenericResponseTO<ExtratoVendaTO>> extratoVendas(@RequestHeader Integer empresaId, @RequestParam String token, @RequestParam String eventoId) {
		
		GenericResponseTO<ExtratoVendaTO> result;
		try{
			ExtratoVendaTO data = service.extratoVendas(empresaId, token, eventoId);
			result = new GenericResponseTO<ExtratoVendaTO>(true, null, data);
		}catch (BusinessException e) {
			result = new GenericResponseTO<ExtratoVendaTO>(false, e.getMessage(), null);
		}catch (Exception e) {
			e.printStackTrace();
			result = new GenericResponseTO<ExtratoVendaTO>(false, Constants.DEFAULT_ERROR_MESSAGE, null);
		}
		
		return ResponseEntity.ok().body(result);
		
	}
	
	@GetMapping("/obterdadoscliente")
	public ResponseEntity<GenericResponseTO<DadosClienteTO>> obterDadosCliente(@RequestHeader Integer empresaId, @RequestParam String token, @RequestParam String telefone) {
		
		GenericResponseTO<DadosClienteTO> result;
		try{
			DadosClienteTO data = service.obterDadosCliente(empresaId, token, telefone);
			result = new GenericResponseTO<DadosClienteTO>(true, null, data);
		}catch (BusinessException e) {
			result = new GenericResponseTO<DadosClienteTO>(false, e.getMessage(), null);
		}catch (Exception e) {
			e.printStackTrace();
			result = new GenericResponseTO<DadosClienteTO>(false, Constants.DEFAULT_ERROR_MESSAGE, null);
		}
		
		return ResponseEntity.ok().body(result);
		
	}
	
	@GetMapping("/listarrotas")
	public ResponseEntity<GenericCollectionResponseTO<RotaTO, List<RotaTO>>> listarRotas(@RequestHeader Integer empresaId, @RequestParam String token) {
		
		GenericCollectionResponseTO<RotaTO, List<RotaTO>> result;
		try{
			List<RotaTO> data = service.listarRotas(token, empresaId);
			result = new GenericCollectionResponseTO<RotaTO, List<RotaTO>>(true, null, data);
		}catch (BusinessException e) {
			result = new GenericCollectionResponseTO<RotaTO, List<RotaTO>>(false, e.getMessage(), null);
		}catch (Exception e) {
			result = new GenericCollectionResponseTO<RotaTO, List<RotaTO>>(false, Constants.DEFAULT_ERROR_MESSAGE, null);
		}
		
		return ResponseEntity.ok().body(result);
	}
	
	@PostMapping("/cadastrarrota")
	public ResponseEntity<GenericResponseTO<Rota>> salvarRota(@RequestHeader Integer empresaId, @RequestParam String token, @RequestBody RotaTO rotaTO) {
		
		GenericResponseTO<Rota> result;
		try{
			Rota data = service.salvarRota(token, empresaId, rotaTO);
			result = new GenericResponseTO<Rota>(true, null, data);
		}catch (BusinessException e) {
			result = new GenericResponseTO<Rota>(false, e.getMessage(), null);
		}catch (Exception e) {
			e.printStackTrace();
			result = new GenericResponseTO<Rota>(false, Constants.DEFAULT_ERROR_MESSAGE, null);
		}
		
		return ResponseEntity.ok().body(result);
	}
	
	@PostMapping("/excluirrota")
	public ResponseEntity<EmptyResponseTO> excluirRota(@RequestHeader Integer empresaId, @RequestParam String token, @RequestParam Integer rotaId) {
		
		EmptyResponseTO result;
		try{
			service.excluirRota(token, empresaId, rotaId);
			result = new EmptyResponseTO(true, null);
		}catch (BusinessException e) {
			result = new EmptyResponseTO(false, e.getMessage());
		}catch (Exception e) {
			result = new EmptyResponseTO(false, Constants.DEFAULT_ERROR_MESSAGE);
		}
		
		return ResponseEntity.ok().body(result);
	}
	
	
	
	
	@GetMapping("/listarcambistas")
	public ResponseEntity<GenericCollectionResponseTO<CambistaTO, List<CambistaTO>>> listarCambistas(@RequestHeader Integer empresaId, @RequestParam String token) {
		
		GenericCollectionResponseTO<CambistaTO, List<CambistaTO>> result;
		try{
			List<CambistaTO> data = service.listarCambistas(token, empresaId);
			result = new GenericCollectionResponseTO<CambistaTO, List<CambistaTO>>(true, null, data);
		}catch (BusinessException e) {
			result = new GenericCollectionResponseTO<CambistaTO, List<CambistaTO>>(false, e.getMessage(), null);
		}catch (Exception e) {
			result = new GenericCollectionResponseTO<CambistaTO, List<CambistaTO>>(false, Constants.DEFAULT_ERROR_MESSAGE, null);
		}
		
		return ResponseEntity.ok().body(result);
	}
	
	@PostMapping("/cadastrarcambista")
	public ResponseEntity<GenericResponseTO<Cambista>> salvarCambista(@RequestHeader Integer empresaId, @RequestParam String token, @RequestBody CambistaTO cambistaTO) {
		
		GenericResponseTO<Cambista> result;
		try{
			Cambista data = service.salvarCambista(token, empresaId, cambistaTO);
			result = new GenericResponseTO<Cambista>(true, null, data);
		}catch (BusinessException e) {
			result = new GenericResponseTO<Cambista>(false, e.getMessage(), null);
		}catch (Exception e) {
			e.printStackTrace();
			result = new GenericResponseTO<Cambista>(false, Constants.DEFAULT_ERROR_MESSAGE, null);
		}
		
		return ResponseEntity.ok().body(result);
	}
	
	@PostMapping("/excluircambista")
	public ResponseEntity<EmptyResponseTO> excluirCambista(@RequestHeader Integer empresaId, @RequestParam String token, @RequestParam Integer cambistaId) {
		
		EmptyResponseTO result;
		try{
			service.excluirCambista(token, empresaId, cambistaId);
			result = new EmptyResponseTO(true, null);
		}catch (BusinessException e) {
			result = new EmptyResponseTO(false, e.getMessage());
		}catch (Exception e) {
			result = new EmptyResponseTO(false, Constants.DEFAULT_ERROR_MESSAGE);
		}
		
		return ResponseEntity.ok().body(result);
	}
}
