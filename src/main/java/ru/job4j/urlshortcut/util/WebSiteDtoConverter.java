package ru.job4j.urlshortcut.util;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.RandomStringUtils;
import ru.job4j.urlshortcut.dto.WebSiteDto;
import ru.job4j.urlshortcut.model.WebSite;

@UtilityClass
public class WebSiteDtoConverter {

    private static final int STRING_SIZE = 7;

    /**
     * Конвертируем WebSiteDto в WebSite перед первичным сохранением в базу данных.
     * При конвертации генерируем логин и пароль размером STRING_SIZE.
     * @param webSiteDto
     * @return WebSite
     */
    public WebSite convertForSave(WebSiteDto webSiteDto) {
        WebSite webSite = new WebSite();
        webSite.setSite(webSiteDto.getSite());
        webSite.setLogin(RandomStringUtils.randomAlphabetic(STRING_SIZE));
        webSite.setPassword(RandomStringUtils.randomAlphanumeric(STRING_SIZE));
        return webSite;
    }

}
