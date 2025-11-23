package br.com.fiap.universidade_fiap.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.fiap.universidade_fiap.mensageria.RabbitMQProdutor;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class RabbitController {
	
	@Autowired
	private RabbitMQProdutor mqProdutor;
	
	@GetMapping("/msg_rabbit")
	public ModelAndView retornarPagRabbit(HttpServletRequest req) {
		ModelAndView mv = new ModelAndView("/rabbit/mensagem");
		mv.addObject("uri", req.getRequestURI());
		return mv;
	}
	
	@PostMapping("/enviar_mensagem_rabbitmq")
	public ModelAndView enviarMensagemRabbit(HttpServletRequest req , @RequestParam(name = "mensagem") String mensagem) {
		mqProdutor.enviarMensagem(mensagem);
		ModelAndView mv = new ModelAndView("/rabbit/mensagem");
		mv.addObject("uri", req.getRequestURI());
		mv.addObject("sucesso", "Mensagem enviado com sucesso!");
		return mv;	
	}
	
	

}
