package com.example.demo.controllers;

import com.example.demo.entities.Filme;
import com.example.demo.service.FilmeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/filmes")
public class FilmeController {
    @Autowired
    private FilmeService service;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @GetMapping(value = "/listar")
    public ResponseEntity<List<Filme>> listaTodos(){
        try {
            List<Filme> listaDeFilme = service.listarTodos();
            return ResponseEntity.ok().body(listaDeFilme);
        } catch (NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
    }

    @PostMapping(value = "/cadastrar")
    public ResponseEntity<Filme> cadastrarFilme(@RequestBody @Valid Filme filme){
        try {
            Filme filmeCriado = service.cadastraFilme(filme);
            return ResponseEntity.ok().body(filmeCriado);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping(value = "/atualizar/{id}")
    public ResponseEntity<Filme> atualizarFilme(@PathVariable Long id, @RequestBody @Valid Filme filme){
        try {
            Filme filmeAtualizado = service.atualizaFilme(id, filme);
            return ResponseEntity.ok().body(filmeAtualizado);
        } catch (NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping(value = "/deleta/{id}")
    public ResponseEntity<Void> deletaFilme(@PathVariable Long id){
        try {
            service.deletaFilme(id);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping(value = "/buscar-por-diretor")
    public ResponseEntity<List<Filme>> buscaPorDiretor(@RequestParam String nomeDiretor){
        try {
            List<Filme> listaFilmes = service.buscaPorNomeOuSobrenomeDiretor(nomeDiretor);
            if (listaFilmes.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
            }
            else {
                return ResponseEntity.ok().body(listaFilmes);
            }
        } catch (NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Filme> buscaPorId(@PathVariable Long id){
        try {
            Filme filme = service.buscaPorId(id);
            if (filme != null){
                return ResponseEntity.ok().body(filme);
            }
            else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping(value = "/busca-por-nome")
    public ResponseEntity<List<Filme>> buscaPorNomeFilme(@RequestParam String nomeFilme){
        try {
            List<Filme> filmesEncontrados = service.buscaPorNome(nomeFilme);
            if (filmesEncontrados.isEmpty()){
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
            }
            else {
                return ResponseEntity.ok().body(filmesEncontrados);
            }
        } catch (NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping(value = "/busca-filme-por-nota/{nota}")
    public ResponseEntity<List<Filme>> buscaFilmePorNota(@PathVariable Integer nota){
        try {
            List<Filme> filmesEncontrados = service.buscaPorAvaliacaoFilmeMaiorQue(nota);
            if (filmesEncontrados.isEmpty()){
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
            }
            else {
                return ResponseEntity.ok().body(filmesEncontrados);
            }
        } catch (NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping(value = "/busca-por-data")
    public ResponseEntity<List<Filme>> buscaFilmePorData(@RequestParam String dataInicioString, @RequestParam String dataFimString){
        try {
            LocalDate dataInicio = LocalDate.parse(dataInicioString, formatter);
            LocalDate dataFim = LocalDate.parse(dataFimString, formatter);
            List<Filme> filmesEncontrados = service.buscaPorIntervaloDeData(LocalDate.from(dataInicio.atStartOfDay()), LocalDate.from(dataFim.atTime(LocalTime.MAX)));
            if (filmesEncontrados.isEmpty()){
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
            }
            else {
                return ResponseEntity.ok().body(filmesEncontrados);
            }
        } catch(NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
