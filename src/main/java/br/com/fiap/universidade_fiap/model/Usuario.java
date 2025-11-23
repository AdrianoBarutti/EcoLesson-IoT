package br.com.fiap.universidade_fiap.model;

import jakarta.persistence.*;
import org.hibernate.validator.constraints.br.CPF;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "t_usuarios", uniqueConstraints = {@UniqueConstraint(columnNames = {"cpf"})})
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;

    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @Column(name = "email_usuario", length = 100, nullable = false, unique = true)
    private String emailUsuario;

    @Column(name = "senha", length = 100, nullable = false)
    private String senha;

    @Column(name = "cadastro")
    private LocalDate cadastro = LocalDate.now();

    @CPF(message = "CPF inválido")
    @Column(name = "cpf", length = 14, unique = true)
    private String cpf;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "usuario_funcao_tab",
        joinColumns = @JoinColumn(name = "id_usuario"),
        inverseJoinColumns = @JoinColumn(name = "id_funcao")
    )
    private Set<Funcao> funcoes = new HashSet<>();

    public Usuario() {}



    public Long getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Long idUsuario) { this.idUsuario = idUsuario; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmailUsuario() { return emailUsuario; }
    public void setEmailUsuario(String emailUsuario) { this.emailUsuario = emailUsuario; }
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
    public LocalDate getCadastro() { return cadastro; }
    public void setCadastro(LocalDate cadastro) { this.cadastro = cadastro; }
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public Set<Funcao> getFuncoes() { return funcoes; }
    public void setFuncoes(Set<Funcao> funcoes) { this.funcoes = funcoes; }
}
