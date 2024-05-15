package com.example.demo.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "tb_filmes")
public class Filme {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @NotBlank
    private String nomeFilme;

    @Getter
    @Setter
    @NotNull
    private LocalDate dataLancamento;

    @NotNull
    @Getter
    @Setter
    @Min(1)
    @Max(5)
    private Integer avaliacaoFilme;

    private String classificacaoFilme;

    @NotBlank
    @Getter
    @Setter
    private String diretorFilme;

    public Filme() {
    }

    public Filme(Long id, String nomeFilme, LocalDate dataLancamento, Integer avaliacaoFilme, String diretorFilme) {
        this.id = id;
        this.nomeFilme = nomeFilme;
        this.dataLancamento = dataLancamento;
        this.avaliacaoFilme = avaliacaoFilme;
        this.classificacaoFilme = getClassificacaoFilme(avaliacaoFilme);
        this.diretorFilme = diretorFilme;
    }

    public String getClassificacaoFilme(Integer notaFilme) {
        return setClassificacaoFilme(notaFilme);
    }

    public String setClassificacaoFilme(Integer notaFilme) {
        switch (notaFilme) {
            case 1 -> {
                return "Ruim!";
            }
            case 2 -> {
                return "Ok!";
            }
            case 3 -> {
                return "Bom!";
            }
            case 4 -> {
                return "Excelente!";
            }
            case 5 -> {
                return "Absolute Cinema!";
            }
        }
        return "TBD";
    }

}