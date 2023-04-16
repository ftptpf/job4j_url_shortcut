package ru.job4j.urlshortcut.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.urlshortcut.dto.WebSiteDto;
import ru.job4j.urlshortcut.model.WebSite;
import ru.job4j.urlshortcut.service.WebSiteService;
import ru.job4j.urlshortcut.util.WebSiteDtoConverter;

import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/url")
@AllArgsConstructor
public class WebSiteController {

    private final WebSiteService webSiteService;

    @PostMapping("/registration")
    public ResponseEntity<?> create(@Valid @RequestBody WebSiteDto webSiteDto) {
        WebSite webSite = WebSiteDtoConverter.convertForSave(webSiteDto);
        WebSite webSiteFromDb = webSiteService.save(webSite);
        Map<String, String> body = new LinkedHashMap<>();
        body.put("registration", "true");
        body.put("login", webSiteFromDb.getLogin());
        body.put("password", webSiteFromDb.getPassword());
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(body);
    }

}
