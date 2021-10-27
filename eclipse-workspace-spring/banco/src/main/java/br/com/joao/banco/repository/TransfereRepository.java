package br.com.joao.banco.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.joao.banco.modelo.Transfere;


public interface TransfereRepository extends JpaRepository<Transfere, Long> {

}
