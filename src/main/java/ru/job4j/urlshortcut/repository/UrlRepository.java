package ru.job4j.urlshortcut.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.job4j.urlshortcut.model.Url;

import java.util.List;
import java.util.Optional;

public interface UrlRepository extends CrudRepository<Url, Integer> {

    @Override
    List<Url> findAll();

    Optional<Url> findByCode(String code);

    @Modifying
    @Query("UPDATE Url u SET u.counter = u.counter + 1 WHERE u.id = :fId")
    void increaseCounterByOne(@Param("fId") int id);

}
