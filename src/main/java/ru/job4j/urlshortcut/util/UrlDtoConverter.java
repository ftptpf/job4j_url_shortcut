package ru.job4j.urlshortcut.util;

import org.apache.commons.lang3.RandomStringUtils;
import ru.job4j.urlshortcut.dto.UrlDto;
import ru.job4j.urlshortcut.model.Url;

public class UrlDtoConverter {

    private static final int STRING_SIZE = 7;

    /**
     * Конвертируем UrlDto в Url перед первичным сохранением в базу данных.
     * При конвертации генерируем код размером STRING_SIZE и устанавливаем счетчик в ноль.
     * @param urlDto
     * @return Url
     */

    public Url convertForSave(UrlDto urlDto) {
        Url url = new Url();
        url.setUrl(urlDto.getUrl());
        url.setCode(RandomStringUtils.randomAlphanumeric(STRING_SIZE));
        url.setCounter(0);
        return url;
    }
}
