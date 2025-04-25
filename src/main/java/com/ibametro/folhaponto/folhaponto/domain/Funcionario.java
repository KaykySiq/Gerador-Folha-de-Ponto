package com.ibametro.folhaponto.folhaponto.domain;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table (name = "funcionario")
public class Funcionario {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "matricula", nullable = false)
    private String matricula;


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Long getId() {
        return id;
    }
}
