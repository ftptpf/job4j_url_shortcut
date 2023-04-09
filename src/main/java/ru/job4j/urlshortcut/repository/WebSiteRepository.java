package ru.job4j.urlshortcut.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.urlshortcut.model.WebSite;

import java.util.Optional;

public interface WebSiteRepository extends CrudRepository<WebSite, Integer> {

    Optional<WebSite> findByLogin(String login);
}
