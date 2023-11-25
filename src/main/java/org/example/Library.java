package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Library {
    private static final Logger log = LoggerFactory.getLogger(Library.class);

    public static void main(String[] args) {
        SpringApplication.run(Library.class, args);
    }
}