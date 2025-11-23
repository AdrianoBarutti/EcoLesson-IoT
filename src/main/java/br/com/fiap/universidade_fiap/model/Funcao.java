package br.com.fiap.universidade_fiap.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "funcao")
public class Funcao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, unique = true)
    private String nome; // "ALUNO" ou "PROFESSOR"

    @ManyToMany(mappedBy = "funcoes")
    private Set<Usuario> usuarios = new HashSet<>();

    public Funcao() {}

    public Funcao(String nome) { this.nome = nome; }

   
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}
    public Set<Usuario> getUsuarios() {return usuarios;}
    public void setUsuarios(Set<Usuario> usuarios) {this.usuarios = usuarios;}
}
