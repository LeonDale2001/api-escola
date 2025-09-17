package com.example.api_escola;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class ApiEscolaApplication {
    public static void main(String[] args) {
        // Carrega automaticamente o .env
        Dotenv dotenv = Dotenv.configure()
                               .directory("./") // raiz do projeto
                               .ignoreIfMalformed()
                               .ignoreIfMissing()
                               .load();

        // Define variáveis do sistema para o Spring Boot ler
        System.setProperty("DB_URL", dotenv.get("DB_URL"));
        System.setProperty("DB_USERNAME", dotenv.get("DB_USERNAME"));
        System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));

        SpringApplication.run(ApiEscolaApplication.class, args);
    }
}
