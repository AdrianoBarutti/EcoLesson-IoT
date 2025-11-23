package br.com.fiap.universidade_fiap.service;

import br.com.fiap.universidade_fiap.model.Certificado;
import br.com.fiap.universidade_fiap.model.Curso;
import br.com.fiap.universidade_fiap.model.Usuario;
import br.com.fiap.universidade_fiap.repository.CertificadoRepository;
import br.com.fiap.universidade_fiap.repository.CursoRepository;
import br.com.fiap.universidade_fiap.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class CertificadoService {

    @Autowired
    private CertificadoRepository certificadoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Transactional
    public Certificado gerarCertificado(String emailUsuario, Long idCurso) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmailUsuario(emailUsuario);
        Optional<Curso> cursoOpt = cursoRepository.findById(idCurso);

        if(usuarioOpt.isEmpty() || cursoOpt.isEmpty()) {
            throw new IllegalArgumentException("Usuário ou curso não encontrado para geração de certificado.");
        }

        Usuario usuario = usuarioOpt.get();
        Curso curso = cursoOpt.get();

        Certificado certificado = new Certificado();
        certificado.setUsuario(usuario);
        certificado.setCurso(curso);
        certificado.setDtEmissao(LocalDate.now());
        certificado.setDescricao("Certificado de conclusão do curso " + curso.getNomeCurso());
        certificado.setCodigoValidacao("VAL" + System.currentTimeMillis()); // exemplo simples de código

        return certificadoRepository.save(certificado);
    }
}
