package ru.job4j.urlshortcut.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.urlshortcut.dto.UrlDto;
import ru.job4j.urlshortcut.model.Url;
import ru.job4j.urlshortcut.service.UrlService;
import ru.job4j.urlshortcut.util.UrlDtoConverter;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/v1/url")
@AllArgsConstructor
public class UrlController {

    private final UrlService urlService;

    @PostMapping("/convert")
    public ResponseEntity<?> create(@Valid @RequestBody UrlDto urlDto) {
        Url url = new UrlDtoConverter().convertForSave(urlDto);
        Url urlFromDb = urlService.save(url);
        String body = urlFromDb.getCode();
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(body);
    }

    @GetMapping("/redirect/{code}")
    public ResponseEntity<?> redirect(@PathVariable String code) {
        Url url = urlService.findByCode(code)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Redirect URL not found. Try other link.")
                );
        return ResponseEntity.status(HttpStatus.FOUND)
                .header("HTTP CODE - 302 REDIRECT", url.getUrl())
                .build();
    }

}
