package br.com.joao.banco.controller;

import java.net.URI;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.joao.banco.config.validacao.Exceptions;
import br.com.joao.banco.controller.dto.ClienteDto;
import br.com.joao.banco.controller.form.ClienteForm;
import br.com.joao.banco.modelo.Cliente;
import br.com.joao.banco.repository.ClienteRepository;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired 
	private ClienteRepository clienteRepository;
	
	
	
	
	@GetMapping
	public List<ClienteDto> lista(String cpf){
	
		if(cpf == null) {
			List<Cliente> clientes = clienteRepository.findAll();
			return ClienteDto.converter(clientes);
		} else {
			List<Cliente> clientes = clienteRepository.findBycpf(cpf);
			return ClienteDto.converter(clientes);
			
		}
		
	}
	
	@PostMapping
	public ResponseEntity cadastrar(@RequestBody @Valid ClienteForm clienteForm, UriComponentsBuilder uriBuilder) {
		if(clienteRepository.existsByCpf(clienteForm.getCpf())) {
			
			return ResponseEntity.badRequest().body(new Exceptions(new Date(), "Cpf já cadastrado."));
		}
		
		if(clienteRepository.existsByCpf(clienteForm.getCpf())) {
			
			return ResponseEntity.badRequest().body(new Exceptions(new Date(), "Esse cpf já possui uma conta cadastrada"));
		}
		
		if(!clienteForm.getCpf().matches("[0-9]+") || clienteForm.getCpf().length() != 11 ) {
			return ResponseEntity.badRequest().body(new Exceptions(new Date(), "CPF inválido!"));
		}

		Cliente cliente = clienteForm.converter(clienteRepository);
		clienteRepository.save(cliente);
		URI uri = uriBuilder.path("/clientes/{id}").buildAndExpand(cliente.getId()).toUri();
		return ResponseEntity.created(uri).body(new ClienteDto(cliente));
		
	}
	
	
}
