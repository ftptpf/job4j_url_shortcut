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

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/url")
@AllArgsConstructor
public class WebSiteController {

    private final WebSiteService webSiteService;

/*    @PostMapping("/registration")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<WebSite> create(@RequestBody WebSiteDto webSiteDto) {
        WebSite webSite = new WebSiteDtoConverter().convertForSave(webSiteDto);
        return new ResponseEntity<>(webSiteService.save(webSite), HttpStatus.CREATED);
    }*/

/*    @PostMapping("/registration")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, String> create(@RequestBody WebSiteDto webSiteDto) {
        WebSite webSite = new WebSiteDtoConverter().convertForSave(webSiteDto);
        WebSite webSiteFromBb = webSiteService.save(webSite);
        return Map.of("registration", "true",
                "login", webSiteFromBb.getLogin(),
                "password", webSiteFromBb.getPassword());
    }*/

    @PostMapping("/registration")
    public ResponseEntity<?> create(@RequestBody WebSiteDto webSiteDto) {
        WebSite webSite = new WebSiteDtoConverter().convertForSave(webSiteDto);
        WebSite webSiteFromBb = webSiteService.save(webSite);
        Map<String, String> body = new LinkedHashMap<>();
        body.put("registration", "true");
        body.put("login", webSiteFromBb.getLogin());
        body.put("password", webSiteFromBb.getPassword());
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(body);
    }

}
