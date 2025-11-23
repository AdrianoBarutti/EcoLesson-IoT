package br.com.fiap.universidade_fiap.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "t_certificado")
public class Certificado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_certificado")
    private Long idCertificado;

    @Column(name = "dt_emissao")
    private LocalDate dtEmissao;

    @Column(name = "descricao", length = 200)
    private String descricao;

    @Column(name = "codigo_validacao", length = 50)
    private String codigoValidacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_curso", nullable = false)
    private Curso curso;

    // Getters e Setters
    public Long getIdCertificado() { return idCertificado; }
    public void setIdCertificado(Long idCertificado) { this.idCertificado = idCertificado; }
    public LocalDate getDtEmissao() { return dtEmissao; }
    public void setDtEmissao(LocalDate dtEmissao) { this.dtEmissao = dtEmissao; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public String getCodigoValidacao() { return codigoValidacao; }
    public void setCodigoValidacao(String codigoValidacao) { this.codigoValidacao = codigoValidacao; }
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    public Curso getCurso() { return curso; }
    public void setCurso(Curso curso) { this.curso = curso; }
}
