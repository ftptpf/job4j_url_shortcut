package ru.job4j.urlshortcut;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UrlShortcutApplication {

    public static void main(String[] args) {
        SpringApplication.run(UrlShortcutApplication.class, args);
        System.out.println("Go to Swagger RestFul API information page http://localhost:8080/swagger-ui.html");
    }

}
