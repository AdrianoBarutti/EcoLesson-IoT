package br.com.fiap.universidade_fiap.mensageria;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
	
	public static final String FILA = "fila-fiap";
	public static final String ROTEADOR = "roteador-fiap";
	public static final String CHAVE_ROTA = "chave-fiap";
	
	@Bean
	public Queue queue() {
		Queue fila = new Queue(FILA,true);
		return fila;
	}
	
	@Bean
	public DirectExchange directExchange() {
		DirectExchange roteador = new DirectExchange(ROTEADOR);
		return roteador;
	}
	
	@Bean
	public Binding binding(Queue queue, DirectExchange directExchange) {
		return BindingBuilder.bind(queue).to(directExchange).with(CHAVE_ROTA);
	}

}
