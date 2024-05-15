package com.example.demo.repositories;

import com.example.demo.entities.Filme;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface FilmeRepository extends JpaRepository<Filme, Long> {
    List<Filme> findByDataLancamentoBetween(LocalDate dataInicio, LocalDate dataFim);
    List<Filme> findByNomeFilmeContainingIgnoreCase(String nomeFilme);
    List<Filme> findByAvaliacaoFilmeGreaterThanEqual(Integer avaliacaoFilme);

    List<Filme> findByDiretorFilmeContainingIgnoreCase(String diretorFilme);
}
