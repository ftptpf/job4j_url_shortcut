package ru.job4j.urlshortcut.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.job4j.urlshortcut.model.WebSite;
import ru.job4j.urlshortcut.repository.WebSiteRepository;

import java.util.Optional;

import static java.util.Collections.emptyList;

@Service
@AllArgsConstructor
public class WebSiteService implements UserDetailsService {

    private final WebSiteRepository webSiteRepository;

    public WebSite save(WebSite webSite) {
        return webSiteRepository.save(webSite);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<WebSite> webSite = webSiteRepository.findByLogin(username);
        if (webSite.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }
        return new User(webSite.get().getLogin(), webSite.get().getPassword(), emptyList());
    }

}
