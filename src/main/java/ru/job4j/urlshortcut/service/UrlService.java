package ru.job4j.urlshortcut.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.urlshortcut.model.Url;
import ru.job4j.urlshortcut.repository.UrlRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class UrlService {

    private final UrlRepository urlRepository;

    public List<Url> findAll() {
        return urlRepository.findAll();
    }

    public Url findByCode(String code) {
        return urlRepository.findByCode(code);
    }
}
