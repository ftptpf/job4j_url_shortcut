package ru.job4j.urlshortcut.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.urlshortcut.dto.WebSiteRegistrationDto;
import ru.job4j.urlshortcut.model.WebSite;
import ru.job4j.urlshortcut.service.WebSiteService;

@RestController
@RequestMapping("/api/v1/url")
@AllArgsConstructor
public class WebSiteController {

    private final WebSiteService webSiteService;

    @PostMapping("/registration")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<WebSite> create(@RequestBody WebSiteRegistrationDto webSiteRegistrationDto) {
        return new ResponseEntity<>(webSiteService.save(new WebSite()), HttpStatus.CREATED);
    }




}
