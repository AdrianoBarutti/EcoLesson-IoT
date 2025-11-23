package br.com.fiap.universidade_fiap.service;

import br.com.fiap.universidade_fiap.model.Certificado;
import br.com.fiap.universidade_fiap.model.Curso;
import br.com.fiap.universidade_fiap.model.Usuario;
import br.com.fiap.universidade_fiap.repository.CertificadoRepository;
import br.com.fiap.universidade_fiap.repository.CursoRepository;
import br.com.fiap.universidade_fiap.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private CertificadoRepository certificadoRepository;

    public Usuario findByEmailUsuario(String emailUsuario) {
        return usuarioRepository.findByEmailUsuario(emailUsuario)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    public List<Curso> findCursosDisponiveis(Long usuarioId) {
        // implementar regra para cursos não inscritos pelo usuário
        return cursoRepository.findCursosDisponiveisParaUsuario(usuarioId);
    }

    public List<Certificado> findCertificados(Long usuarioId) {
        return certificadoRepository.findCertificadosPorUsuario(usuarioId);
    }

    public List<Curso> findCursosMinistrados(Long usuarioId) {
        return cursoRepository.findCursosMinistradosPorProfessor(usuarioId);
    }
}
