package br.com.fiap.universidade_fiap.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "t_curso")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_curso")
    private Long idCurso;

    @Column(name = "nome_curso", length = 150, nullable = false)
    private String nomeCurso;

    @Column(name = "descricao", columnDefinition = "CLOB")
    private String descricao;

    @Column(name = "qt_horas")
    private Integer qtHoras;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_professor") // Coluna que referencia o professor na tabela de curso
    private Usuario professor;

    @OneToMany(mappedBy = "curso", fetch = FetchType.LAZY)
    private Set<Certificado> certificados = new HashSet<>();

    // Getters e Setters
    public Long getIdCurso() { return idCurso; }
    public void setIdCurso(Long idCurso) { this.idCurso = idCurso; }
    public String getNomeCurso() { return nomeCurso; }
    public void setNomeCurso(String nomeCurso) { this.nomeCurso = nomeCurso; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public Integer getQtHoras() { return qtHoras; }
    public void setQtHoras(Integer qtHoras) { this.qtHoras = qtHoras; }
    public Usuario getProfessor() { return professor; }
    public void setProfessor(Usuario professor) { this.professor = professor; }
    public Set<Certificado> getCertificados() { return certificados; }
    public void setCertificados(Set<Certificado> certificados) { this.certificados = certificados; }
}
