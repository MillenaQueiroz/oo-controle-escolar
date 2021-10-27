package br.com.joao.banco.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.joao.banco.modelo.Cliente;


public interface ClienteRepository extends JpaRepository<Cliente, Long> {

		Cliente findByCpf(String cpf);
		boolean existsByCpf(String cpf);
		List<Cliente> findBycpf(String cpf);
		
}

