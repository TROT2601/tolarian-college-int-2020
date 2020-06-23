package com.jeremy;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibraryApp implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(LibraryApp.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
