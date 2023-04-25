package ru.job4j.urlshortcut.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.urlshortcut.model.Url;
import ru.job4j.urlshortcut.repository.UrlRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UrlService {

    private final UrlRepository urlRepository;

    public Url save(Url url) {
        return urlRepository.save(url);
    }

    public List<Url> findAll() {
        return urlRepository.findAll();
    }

    @Transactional
    public Optional<Url> findByCode(String code) {
        Optional<Url> url = urlRepository.findByCode(code);
        url.ifPresent(value -> increaseCounterByOne(value.getId()));
        return url;
    }

    @Transactional
    public void increaseCounterByOne(int id) {
        urlRepository.increaseCounterByOne(id);
    }

}
