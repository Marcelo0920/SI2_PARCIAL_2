package com.parcial2.si2;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class EnvCheckRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        System.out.println("DB_URL: " + System.getProperty("DB_URL"));
        System.out.println("DB_NAME: " + System.getProperty("DB_NAME"));
        System.out.println("DB_PASSWORD: " + System.getProperty("DB_PASSWORD"));
    }
}