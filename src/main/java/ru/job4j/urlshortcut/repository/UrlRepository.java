package ru.job4j.urlshortcut.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.urlshortcut.model.Url;

import java.util.List;

public interface UrlRepository extends CrudRepository<Url, Integer> {

    @Override
    List<Url> findAll();

    Url findByCode(String code);
}
