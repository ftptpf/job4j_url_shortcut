package ru.job4j.urlshortcut.util;

import lombok.experimental.UtilityClass;
import ru.job4j.urlshortcut.dto.UrlStatisticDto;
import ru.job4j.urlshortcut.model.Url;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class UrlStatistic {

    public List<UrlStatisticDto> convert(List<Url> list) {
        List<UrlStatisticDto> listDto = new ArrayList<>();
        list.forEach(url -> listDto.add(
                UrlStatisticDto
                        .builder()
                        .url(url.getUrl())
                        .total(url.getCounter())
                        .build())
        );
        return listDto;
    }

}
