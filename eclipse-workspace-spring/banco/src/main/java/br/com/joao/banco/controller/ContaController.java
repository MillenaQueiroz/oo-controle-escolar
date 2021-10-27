package br.com.joao.banco.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import br.com.joao.banco.config.validacao.Exceptions;
import br.com.joao.banco.controller.dto.ContaDto;
import br.com.joao.banco.controller.dto.TransacoesDto;
import br.com.joao.banco.controller.dto.TransfereDto;
import br.com.joao.banco.controller.form.ContaForm;
import br.com.joao.banco.modelo.Conta;

import br.com.joao.banco.modelo.Transacoes;
import br.com.joao.banco.modelo.Transfere;
import br.com.joao.banco.repository.ClienteRepository;
import br.com.joao.banco.repository.ContaRepository;
import br.com.joao.banco.repository.TransfereRepository;

import br.com.joao.banco.repository.TransacoesRepository;


@RestController 
@RequestMapping("/contas")
public class ContaController {
	
	
	@Autowired
	private ContaRepository contaRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private TransfereRepository transfereRepository;
	
	@Autowired
	private TransacoesRepository transacoesRepository;
	

	
	@GetMapping
	public List<ContaDto> lista(String cpfCliente){
		
		
		if(cpfCliente == null) {
			List<Conta> contas = contaRepository.findAll();
			return ContaDto.converter(contas);
		} else {
			List<Conta> contas = contaRepository.findByTitular_Cpf(cpfCliente);
			return ContaDto.converter(contas);
			
		}
		
	}
	
	@PostMapping
	public ResponseEntity cadastrar(@RequestBody @Valid ContaForm contaForm, UriComponentsBuilder uriBuilder) {
		if(contaRepository.existsByNumero(contaForm.getNumero())) {
			
			return ResponseEntity.badRequest().body(new Exceptions(new Date(), "Numero ja cadastrado."));
		}
		
		if(clienteRepository.existsByCpf(contaForm.getCpfCliente()) == false) {
			
			return ResponseEntity.badRequest().body(new Exceptions(new Date(), "Esse cpf não está cadastrado"));
		}
		
		if(contaRepository.existsByTitular_Cpf(contaForm.getCpfCliente())) {
			
			return ResponseEntity.badRequest().body(new Exceptions(new Date(), "Esse cpf está vinculado à outra conta"));
		}


		Conta conta = contaForm.converter(clienteRepository);
		contaRepository.save(conta);
		URI uri = uriBuilder.path("/contas/{id}").buildAndExpand(conta.getId()).toUri();
		return ResponseEntity.created(uri).body(new ContaDto(conta));
		
	}

	@PostMapping("/transfere")
	@Transactional
	public ResponseEntity transfere(@RequestBody TransfereDto transfereDto) {
		
		Optional<Conta> contaSaida  = contaRepository.findByNumero(transfereDto.getNumContaSaida());
		 if(contaSaida.isEmpty()) {
			 return  ResponseEntity.badRequest().body(new Exceptions(new Date(), " Numero da conta de origem da transferência inexistente."));
		 }
		 Optional<Conta> contaEntrada = contaRepository.findByNumero(transfereDto.getNumContaEntrada());
		 if(contaEntrada.isEmpty()) {
			 return  ResponseEntity.badRequest().body(new Exceptions(new Date(), " Numero da conta destino inexistente."));
		 }
		
		Conta conta = contaSaida.get();
		
		boolean tentativa = conta.testaTransferencia(contaEntrada.get(), transfereDto.getValor());
		if(!tentativa) {
			return  ResponseEntity.badRequest().body(new Exceptions(new Date(), " saldo insuficiente."));
		 
		}
		conta.saca(transfereDto.getValor());
		
		TransacoesDto envioDto = new TransacoesDto(transfereDto.getNumContaSaida(), transfereDto.getValor());
		Transacoes envio = envioDto.toModel();
		envio.setTipoTransacao("Envio de Transferência");
		transacoesRepository.save(envio);
		
		Conta conta2 = contaEntrada.get();
		conta2.deposita(transfereDto.getValor());
		TransacoesDto recebimentoDto = new TransacoesDto(transfereDto.getNumContaEntrada(), transfereDto.getValor());
		Transacoes recebimento = recebimentoDto.toModel();
		recebimento.setTipoTransacao("Recebimento de Transferência");
		transacoesRepository.save(recebimento);
		
		
		Transfere transferencia = transfereDto.toModel();
		transfereRepository.save(transferencia);
		return ResponseEntity.ok(conta);
		
	}
	@PostMapping("/deposita")
	@Transactional
	public ResponseEntity deposita(@RequestBody TransacoesDto depositoDto) {
		
		Optional<Conta> contaDeposito = contaRepository.findByNumero(depositoDto.getNumero());
		if(contaDeposito.isEmpty()) {
			 return  ResponseEntity.badRequest().body(new Exceptions(new Date(), " Numero da conta inexistente."));
		}
		
		
		Conta conta = contaDeposito.get();
		
		
		conta.deposita(depositoDto.getValor());
		Transacoes deposito = depositoDto.toModel();
		deposito.setTipoTransacao("deposito");
		transacoesRepository.save(deposito);
		return ResponseEntity.ok(conta);
		
		
	}
	
	

	@PostMapping("/saca")
	@Transactional
	public ResponseEntity saca(@RequestBody TransacoesDto saqueDto) {
		
		Optional<Conta> contaSaque = contaRepository.findByNumero(saqueDto.getNumero());
		if(contaSaque.isEmpty()) {
			 return  ResponseEntity.badRequest().body(new Exceptions(new Date(), " Numero da conta inexistente."));
		}
		
		
		Conta conta = contaSaque.get();
		
		boolean tentativa = conta.testaTransferencia(contaSaque.get(), saqueDto.getValor());
		
		if(!tentativa) {
			return  ResponseEntity.badRequest().body(new Exceptions(new Date(), " saldo insuficiente."));
		}
	
		conta.saca(saqueDto.getValor());
		Transacoes saca = saqueDto.toModel();
		saca.setTipoTransacao("Saque");
		transacoesRepository.save(saca);
		return ResponseEntity.ok(conta);
		
	}
	

	
	
	@GetMapping("/extrato")
	public List<Transacoes> extrato(@RequestBody TransacoesDto transacaoDto) {
		
		Optional<Conta> transacao = contaRepository.findByNumero(transacaoDto.getNumero());
		if (transacao.isEmpty()) {
			return null;
		}
		Conta conta = transacao.get();
		return transacoesRepository.findByNumero(conta.getNumero());
		
	}
	


	
	

		
		
	
	
	
	
	
	
}
	
	

