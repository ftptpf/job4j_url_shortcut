package ru.job4j.urlshortcut.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.urlshortcut.dto.WebSiteDto;

import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = "/db/scripts/002_dml_delete_from_table_websites.sql")
class WebSiteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void webSiteRegistration() throws Exception {
        WebSiteDto webSiteDto = new WebSiteDto("site5.ru");
        this.mockMvc.perform(post("/api/v1/url/registration")
                        .content(objectMapper.writeValueAsString(webSiteDto))
                        .contentType(MediaType.APPLICATION_JSON) )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(unauthenticated())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.registration").value("true"));

    }
}