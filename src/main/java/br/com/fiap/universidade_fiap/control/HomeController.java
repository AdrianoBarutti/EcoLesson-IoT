package br.com.fiap.universidade_fiap.control;

import br.com.fiap.universidade_fiap.model.Usuario;
import br.com.fiap.universidade_fiap.service.UsuarioService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    private final UsuarioService usuarioService;

    public HomeController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/")
    public ModelAndView home(Authentication authentication) {
        ModelAndView mv = new ModelAndView();

        String username = authentication.getName();
        Usuario usuario = usuarioService.findByEmailUsuario(username);

        boolean isAluno = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(role -> role.equals("ROLE_ALUNO"));
        boolean isProfessor = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(role -> role.equals("ROLE_PROFESSOR"));

        if (isProfessor) {
            mv.setViewName("home/index-professor");
            mv.addObject("usuario", usuario);
            mv.addObject("cursosMinistrados", usuarioService.findCursosMinistrados(usuario.getIdUsuario()));
        } else if (isAluno) {
            mv.setViewName("home/index-aluno");
            mv.addObject("usuario", usuario);
            mv.addObject("cursosDisponiveis", usuarioService.findCursosDisponiveis(usuario.getIdUsuario()));
            mv.addObject("certificados", usuarioService.findCertificados(usuario.getIdUsuario()));
        } else {
            mv.setViewName("acesso-negado");
        }
        return mv;
    }
}
