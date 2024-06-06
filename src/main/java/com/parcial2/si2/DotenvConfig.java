package com.parcial2.si2;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DotenvConfig {

    @Bean
    public Dotenv dotenv() {

        Dotenv dotenv =  Dotenv.load();
        System.out.println(dotenv.get("DB_URL"));
        return dotenv;
    }
}
