package ru.job4j.urlshortcut.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.urlshortcut.dto.UrlDto;
import ru.job4j.urlshortcut.dto.UrlStatisticDto;
import ru.job4j.urlshortcut.model.Url;
import ru.job4j.urlshortcut.service.UrlService;
import ru.job4j.urlshortcut.util.UrlDtoConverter;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


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
        url.setCounter(url.getCounter() + 1);
        urlService.save(url);
        return ResponseEntity.status(HttpStatus.FOUND)
                .header("HTTP CODE - 302 REDIRECT", url.getUrl())
                .build();
    }

    @GetMapping("/statistic")
    public ResponseEntity<?> statistic() {
        List<Url> list = urlService.findAll();
        List<UrlStatisticDto> listDto = new ArrayList<>();
        list.forEach(url -> listDto.add(new UrlStatisticDto(url.getUrl(), url.getCounter())));
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(listDto);
    }

}
