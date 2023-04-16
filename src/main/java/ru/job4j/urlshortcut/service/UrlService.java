package ru.job4j.urlshortcut.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.urlshortcut.model.Url;
import ru.job4j.urlshortcut.repository.UrlRepository;

import java.util.List;

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
    public Url findByCode(String code) {
        Url url = urlRepository.findByCode(code)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Redirect URL not found. Try other link.")
                );
        increaseCounterByOne(url.getId());
        return url;
    }

    @Transactional
    public void increaseCounterByOne(int id) {
        urlRepository.increaseCounterByOne(id);
    }

}
