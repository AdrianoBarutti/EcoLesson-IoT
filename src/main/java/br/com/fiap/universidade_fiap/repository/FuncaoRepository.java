package br.com.fiap.universidade_fiap.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.universidade_fiap.model.Funcao;

public interface FuncaoRepository extends JpaRepository<Funcao, Long> {
    Optional<Funcao> findByNome(String nome);
}