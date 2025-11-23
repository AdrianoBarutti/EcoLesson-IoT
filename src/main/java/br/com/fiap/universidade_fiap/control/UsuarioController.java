package br.com.fiap.universidade_fiap.control;

import br.com.fiap.universidade_fiap.model.Funcao;
import br.com.fiap.universidade_fiap.model.Usuario;
import br.com.fiap.universidade_fiap.repository.FuncaoRepository;
import br.com.fiap.universidade_fiap.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Controller
public class UsuarioController {

    @Autowired
    private FuncaoRepository funcaoRepo;

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public ModelAndView mostrarTelaLogin() {
        return new ModelAndView("login"); 
    }

    @GetMapping("/usuario/novo")
    public ModelAndView mostrarCadastroUsuario() {
        ModelAndView mv = new ModelAndView("usuario/novo");
        mv.addObject("usuario", new Usuario());
        mv.addObject("funcoes", funcaoRepo.findAll());
        return mv;
    }

    @PostMapping("/insere_usuario")
    public ModelAndView criarUsuario(Usuario usuario, @RequestParam("id_funcao") Long idFuncao) {
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        Set<Funcao> funcoes = new HashSet<>();
        funcaoRepo.findById(idFuncao).ifPresent(funcoes::add);
        usuario.setFuncoes(funcoes);
        usuarioRepo.save(usuario);
        return new ModelAndView("redirect:/login");
    }
}
