package br.com.fiap.universidade_fiap.control;

import br.com.fiap.universidade_fiap.model.Certificado;
import br.com.fiap.universidade_fiap.model.Curso;
import br.com.fiap.universidade_fiap.model.Usuario;
import br.com.fiap.universidade_fiap.repository.CertificadoRepository;
import br.com.fiap.universidade_fiap.repository.CursoRepository;
import br.com.fiap.universidade_fiap.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private CertificadoRepository certificadoRepository;

    @Autowired
    private UsuarioService usuarioService;

    // DASHBOARD ALUNO - BATE COM index-aluno.html
    @GetMapping("/index-aluno")
    public String indexAluno(Model model, Authentication authentication) {
        Usuario usuario = usuarioService.findByEmailUsuario(authentication.getName());
        List<Curso> cursosDisponiveis = cursoRepository.findCursosDisponiveisParaUsuario(usuario.getIdUsuario());
        List<Certificado> certificados = certificadoRepository.findCertificadosPorUsuario(usuario.getIdUsuario());
        model.addAttribute("usuario", usuario);
        model.addAttribute("cursosDisponiveis", cursosDisponiveis);
        model.addAttribute("certificados", certificados);
        return "home/index-aluno";
    }

    // DASHBOARD DO PROFESSOR - BATE COM index-professor.html
    @GetMapping("/index-professor")
    public String indexProfessor(Model model, Authentication authentication) {
        Usuario professor = usuarioService.findByEmailUsuario(authentication.getName());
        List<Curso> cursosMinistrados = cursoRepository.findCursosMinistradosPorProfessor(professor.getIdUsuario());
        model.addAttribute("usuario", professor);
        model.addAttribute("cursosMinistrados", cursosMinistrados);
        return "home/index-professor";
    }

    // CRIAÇÃO CURSO
    @PostMapping("/criar")
    public String criarCurso(@ModelAttribute Curso curso, Authentication authentication) {
        Usuario professor = usuarioService.findByEmailUsuario(authentication.getName());
        curso.setProfessor(professor);
        cursoRepository.save(curso);
        return "redirect:/cursos/index-professor";
    }

    // EDIÇÃO CURSO (GET)
    @GetMapping("/editar/{id}")
    public String editarCursoForm(@PathVariable Long id, Model model, Authentication authentication) {
        Curso curso = cursoRepository.findById(id).orElse(null);
        if (curso == null) return "redirect:/cursos/index-professor";
        Usuario professor = usuarioService.findByEmailUsuario(authentication.getName());
        if (!curso.getProfessor().getIdUsuario().equals(professor.getIdUsuario())) {
            return "redirect:/cursos/index-professor";
        }
        model.addAttribute("curso", curso);
        return "curso/form-editar";
    }

    // SALVAR EDIÇÃO (POST)
    @PostMapping("/editar/{id}")
    public String salvarEdicao(@PathVariable Long id, @ModelAttribute Curso curso, Authentication authentication) {
        Usuario professor = usuarioService.findByEmailUsuario(authentication.getName());
        Curso existente = cursoRepository.findById(id).orElse(null);
        if (existente == null) return "redirect:/cursos/index-professor";
        if (!existente.getProfessor().getIdUsuario().equals(professor.getIdUsuario())) {
            return "redirect:/cursos/index-professor";
        }
        existente.setNomeCurso(curso.getNomeCurso());
        existente.setDescricao(curso.getDescricao());
        existente.setQtHoras(curso.getQtHoras());
        cursoRepository.save(existente);
        return "redirect:/cursos/index-professor";
    }



    // REMOVER CURSO
    @GetMapping("/remover/{id}")
    public String removerCurso(@PathVariable Long id, Authentication authentication) {
        Usuario professor = usuarioService.findByEmailUsuario(authentication.getName());
        Curso curso = cursoRepository.findById(id).orElse(null);
        if (curso != null && curso.getProfessor().getIdUsuario().equals(professor.getIdUsuario())) {
            cursoRepository.delete(curso);
        }
        return "redirect:/cursos/index-professor";
    }

    // DETALHES DO CURSO - para o ALUNO acessar qualquer curso
    @GetMapping("/{id}")
    public String mostrarDetalhe(@PathVariable Long id, Model model, Authentication authentication) {
        Usuario usuario = usuarioService.findByEmailUsuario(authentication.getName());
        Curso curso = cursoRepository.findById(id).orElse(null);
        if (curso == null) {
            model.addAttribute("erro", "Curso não encontrado.");
            return "curso/erro";
        }
        model.addAttribute("curso", curso);
        model.addAttribute("usuario", usuario);
        return "curso/detalhe";
    }

    // CONCLUIR CURSO = GERAR CERTIFICADO
    @PostMapping("/{id}/concluir")
    public String concluirCurso(@PathVariable Long id, Authentication authentication) {
        Usuario usuario = usuarioService.findByEmailUsuario(authentication.getName());
        Curso curso = cursoRepository.findById(id).orElse(null);
        if (curso == null) {
            return "redirect:/cursos/index-aluno?erro=cursoNaoEncontrado";
        }
        if(certificadoRepository.findCertificadosPorUsuario(usuario.getIdUsuario())
                .stream().anyMatch(c -> c.getCurso().getIdCurso().equals(id))) {
            return "redirect:/cursos/index-aluno";
        }
        Certificado cert = new Certificado();
        cert.setUsuario(usuario);
        cert.setCurso(curso);
        cert.setDtEmissao(LocalDate.now());
        cert.setDescricao("Certificado de conclusão do curso " + curso.getNomeCurso());
        cert.setCodigoValidacao("VAL" + System.currentTimeMillis());
        certificadoRepository.save(cert);
        return "redirect:/cursos/index-aluno";
    }
}
