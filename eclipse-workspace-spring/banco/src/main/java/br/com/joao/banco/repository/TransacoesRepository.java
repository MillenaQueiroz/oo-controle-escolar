package br.com.joao.banco.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.joao.banco.modelo.Transacoes;


public interface TransacoesRepository extends JpaRepository<Transacoes, Long> {
	List<Transacoes> findByNumero(int numero);
	
}
