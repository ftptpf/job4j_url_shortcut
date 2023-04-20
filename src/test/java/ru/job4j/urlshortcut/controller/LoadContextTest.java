package ru.job4j.urlshortcut.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class LoadContextTest {

    @Autowired
    UrlController urlController;

    @Test
    public void contextLoad() {
        assertThat(urlController).isNotNull();
    }
}
