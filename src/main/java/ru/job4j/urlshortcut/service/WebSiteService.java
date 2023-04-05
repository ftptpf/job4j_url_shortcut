package ru.job4j.urlshortcut.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.urlshortcut.model.WebSite;
import ru.job4j.urlshortcut.repository.WebSiteRepository;

@Service
@AllArgsConstructor
public class WebSiteService {

    private final WebSiteRepository webSiteRepository;

    public WebSite save(WebSite webSite) {
        return webSiteRepository.save(webSite);
    }

}
