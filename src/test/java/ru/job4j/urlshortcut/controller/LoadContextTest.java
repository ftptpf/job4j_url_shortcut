package ru.job4j.urlshortcut.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LoadContextTest {

    @Autowired
    UrlController urlController;

    @Test
    public void contextLoad() {
        Assertions.assertThat(urlController).isNotNull();
    }
}
