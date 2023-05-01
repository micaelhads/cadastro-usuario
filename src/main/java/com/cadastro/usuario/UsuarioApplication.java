package com.cadastro.usuario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@ComponentScan(basePackages = {"com.cadastro.usuario.service", "com.cadastro.usuario.controller","com.cadastro.usuario.exeptions.handler","com.cadastro.usuario.exeptions"})
@EnableJpaRepositories("com.cadastro.usuario.repository")
@SpringBootApplication
@EnableSwagger2
public class UsuarioApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsuarioApplication.class, args);
	}

}
