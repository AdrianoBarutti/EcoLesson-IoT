package br.com.fiap.universidade_fiap.mensageria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProdutor {
	
	public static final String TOPICO = "Universidade-Fiap-Topico";
	
	@Autowired
	private KafkaTemplate<String,String> produtor;
	
	public void enviarMensagem(String mensagem) {
		produtor.send(TOPICO, mensagem);
		System.out.println("Enviando a mensagem (" 
		+ mensagem + ") para o tópico (" + TOPICO + ")");
	}

}
