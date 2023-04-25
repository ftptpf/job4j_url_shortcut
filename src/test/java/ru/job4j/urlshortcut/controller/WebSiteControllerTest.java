package ru.job4j.urlshortcut.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.urlshortcut.dto.WebSiteDto;

import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:db/liquibase-test.properties")
@Sql(scripts = "/db/scripts/002_dml_delete_from_table_websites.sql")
class WebSiteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Sql(scripts = "/db/scripts/002_dml_delete_from_table_websites.sql",
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void whenWebSiteRegistration() throws Exception {
        WebSiteDto webSiteDto = new WebSiteDto("test-site5.ru");
        this.mockMvc.perform(post("/api/v1/url/registration")
                        .content(objectMapper.writeValueAsString(webSiteDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(unauthenticated())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.registration").value("true"));
    }

    @Sql(scripts = "/db/scripts/001_dml_insert_table_websites.sql",
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/db/scripts/002_dml_delete_from_table_websites.sql",
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void whenNotUniqueWebSiteRegistration() throws Exception {
        WebSiteDto notUniqueWebSiteDto = new WebSiteDto("test-site1.ru");
        this.mockMvc.perform(post("/api/v1/url/registration")
                        .content(objectMapper.writeValueAsString(notUniqueWebSiteDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(unauthenticated())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.registration").value("false"))
                .andExpect(jsonPath("$.message")
                        .value("Object can't be save in database. It already exist them."));
    }

}
