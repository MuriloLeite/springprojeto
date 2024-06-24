package com.site.springprojeto.models.entity;

import jakarta.persistence.*;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "desenvolvedor")
public class Desenvolvedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length =14)
    private String CNPJ;

    @Column(length =50)
    private String nome;

    private Integer nota;


    private Date dataCriacao;

    private String presidente;
    private Integer numeroColaboradores;
    private String website;

    @OneToMany(mappedBy = "desenvolvedor", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Jogo> jogos = new HashSet<>();


    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getPresidente() {
        return presidente;
    }

    public void setPresidente(String presidente) {
        this.presidente = presidente;
    }

    public Integer getNumeroColaboradores() {
        return numeroColaboradores;
    }

    public void setNumeroColaboradores(Integer numeroColaboradores) {
        this.numeroColaboradores = numeroColaboradores;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
