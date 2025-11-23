package br.com.fiap.universidade_fiap.control;

import org.springframework.ai.openai.OpenAiChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import br.com.fiap.universidade_fiap.model.Curso;
import br.com.fiap.universidade_fiap.repository.CursoRepository;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
public class SpringAIController {
    @Autowired
    private OpenAiChatClient chatClient;

    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping("/enviar_mensagem_spring_ai_personalizado")
    public ModelAndView enviarPerguntaOpenAIPersonalizada(HttpServletRequest req,
                                                         @RequestParam("pergunta") String pergunta,
                                                         @RequestParam("idCurso") Long idCurso) {
        ModelAndView mv = new ModelAndView("/curso/detalhe");

        Optional<Curso> cursoOpt = cursoRepository.findById(idCurso);
        if (cursoOpt.isEmpty()) {
            mv.addObject("erro", "Curso não encontrado.");
            return mv;
        }
        mv.addObject("curso", cursoOpt.get());
        mv.addObject("resposta", chatClient.call(pergunta));
        mv.addObject("uri", req.getRequestURI());
        return mv;
    }
}
