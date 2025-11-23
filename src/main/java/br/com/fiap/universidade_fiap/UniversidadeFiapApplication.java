package br.com.fiap.universidade_fiap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class UniversidadeFiapApplication {

	public static void main(String[] args) {
		SpringApplication.run(UniversidadeFiapApplication.class, args);
	}

}
