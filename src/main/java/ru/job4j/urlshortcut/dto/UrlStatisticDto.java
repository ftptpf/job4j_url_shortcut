package ru.job4j.urlshortcut.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UrlStatisticDto {

    @NotBlank(message = "URL can't be null or whitespace!")
    private String url;

    @NotNull(message = "Counter can't be null!")
    @PositiveOrZero(message = "Counter must be a positive number or 0!")
    private int total;
}
