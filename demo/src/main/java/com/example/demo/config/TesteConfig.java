package com.example.demo.config;

import com.example.demo.entities.Filme;
import com.example.demo.repositories.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Configuration
@Profile("test")
public class TesteConfig implements CommandLineRunner {
    @Autowired
    private FilmeRepository repository;


    @Override
    public void run(String... args) throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate data1 = LocalDate.parse("2024-05-09", formatter);
        LocalDate data2 = LocalDate.parse("2024-02-29", formatter);
        LocalDate data3 = LocalDate.parse("2011-08-27", formatter);
        LocalDate data4 = LocalDate.parse("2020-10-29", formatter);
        LocalDate data5 = LocalDate.parse("2013-01-18", formatter);

        Filme f1 = new Filme(null, "Kingdom Of Planet of Apes", data1, 4, "Wes Ball");
        Filme f2 = new Filme(null, "The Fall Guy", data2, 4, "David Leitch");
        Filme f3 = new Filme(null, "O Homem do Futuro", data3, 3, "Claudio Torres");
        Filme f4 = new Filme(null, "Tenet", data4, 3, "Christopher Nolan");
        Filme f5 = new Filme(null, "Djando Unchained", data5, 5, "Quentin Tarantino");

        repository.saveAll(Arrays.asList(f1,f2,f3,f4,f5));
    }
}
