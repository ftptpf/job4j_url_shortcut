package ru.job4j.urlshortcut.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.urlshortcut.dto.UrlDto;
import ru.job4j.urlshortcut.model.Url;
import ru.job4j.urlshortcut.service.UrlService;
import ru.job4j.urlshortcut.util.UrlDtoConverter;


@RestController
@RequestMapping("/api/v1/url")
@AllArgsConstructor
public class UrlController {

    private final UrlService urlService;

    @PostMapping("/convert")
    public ResponseEntity<?> create(@RequestBody UrlDto urlDto) {
        Url url = new UrlDtoConverter().convertForSave(urlDto);
        Url urlFromDb = urlService.save(url);
        String body = urlFromDb.getCode();
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(body);
    }

}
