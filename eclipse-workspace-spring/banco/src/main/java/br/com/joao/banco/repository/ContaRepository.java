package br.com.joao.banco.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.joao.banco.modelo.Conta;

public interface ContaRepository extends JpaRepository<Conta, Long>{

	 List<Conta> findByTitular_Cpf(String cpf);
	 boolean existsByNumero(int numero);
	 Optional<Conta> findByNumero(int numero);
	 boolean existsByTitular_Cpf(String cpf);
	 
	
	


}