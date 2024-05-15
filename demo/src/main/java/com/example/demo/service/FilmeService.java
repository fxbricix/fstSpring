package com.example.demo.service;

import com.example.demo.entities.Filme;
import com.example.demo.repositories.FilmeRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class FilmeService {

    @Autowired
    private FilmeRepository repository;

    public Filme cadastraFilme(@Valid Filme filme){
        return repository.save(filme);
    }

    public List<Filme> listarTodos(){
        return repository.findAll();
    }

    public Filme atualizaFilme(Long id, Filme filmeAtualizado){
        if (repository.existsById(id)){
            filmeAtualizado.setId(id);
            return repository.save(filmeAtualizado);
        }
        return null;
    }

    public void deletaFilme(Long id){
        repository.deleteById(id);
    }
    public Filme buscaPorId(Long id){
        Optional<Filme> filme = repository.findById(id);
        return filme.get();
    }

    public List<Filme> buscaPorNome(String nomeFilme){
        return repository.findByNomeFilmeContainingIgnoreCase(nomeFilme);
    }

    public List<Filme> buscaPorIntervaloDeData(LocalDate dataInicio, LocalDate dataFinal){
        return repository.findByDataLancamentoBetween(dataInicio,dataFinal);
    }

    public List<Filme> buscaPorAvaliacaoFilmeMaiorQue(Integer avaliacaoFilme){
        return repository.findByAvaliacaoFilmeGreaterThanEqual(avaliacaoFilme);
    }

    public List<Filme> buscaPorNomeOuSobrenomeDiretor(String diretorFilme){
        return repository.findByDiretorFilmeContainingIgnoreCase(diretorFilme);
    }
}
