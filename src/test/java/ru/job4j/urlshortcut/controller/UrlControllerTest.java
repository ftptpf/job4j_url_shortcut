package ru.job4j.urlshortcut.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.urlshortcut.dto.UrlDto;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = "/db/scripts/004_dml_delete_from_table_urls.sql")
class UrlControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Sql(scripts = "/db/scripts/004_dml_delete_from_table_urls.sql",
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    @WithMockUser
    public void whenUrlRegistration() throws Exception {
        UrlDto urlDto = new UrlDto("test-site1.ru/111/111");
        this.mockMvc.perform(post("/api/v1/url/convert")
                        .content(objectMapper.writeValueAsString(urlDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(authenticated())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$").exists())
                .andExpect(content().string(Matchers.hasLength(7)));
    }

    @Sql(scripts = "/db/scripts/003_dml_insert_table_urls.sql",
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/db/scripts/004_dml_delete_from_table_urls.sql",
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    @WithMockUser
    public void whenNotUniqueUrlRegistration() throws Exception {
        UrlDto urlDto = new UrlDto("test-site1.ru/56546/88643");
        this.mockMvc.perform(post("/api/v1/url/convert")
                        .content(objectMapper.writeValueAsString(urlDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(authenticated())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.registration").value("false"))
                .andExpect(jsonPath("$.message")
                        .value("Object can't be save in database. It already exist them."));
    }

    @Sql(scripts = "/db/scripts/003_dml_insert_table_urls.sql",
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/db/scripts/004_dml_delete_from_table_urls.sql",
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void whenGetRedirectUrl() throws Exception {
        this.mockMvc.perform(get("/api/v1/url/redirect/1111111"))
                .andDo(print())
                .andExpect(unauthenticated())
                .andExpect(status().isFound())
                .andExpect(header().string("HTTP CODE - 302 REDIRECT", "test-site1.ru/56546/88643"));
    }

    @Sql(scripts = "/db/scripts/003_dml_insert_table_urls.sql",
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/db/scripts/004_dml_delete_from_table_urls.sql",
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void whenGetRedirectUrlWrongCode() throws Exception {
        this.mockMvc.perform(get("/api/v1/url/redirect/222"))
                .andDo(print())
                .andExpect(unauthenticated())
                .andExpect(status().isNotFound())
                .andExpect(status().reason(containsString("Redirect URL not found. Try other link.")));
    }

    @Sql(scripts = "/db/scripts/003_dml_insert_table_urls.sql",
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/db/scripts/004_dml_delete_from_table_urls.sql",
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    @WithMockUser
    public void whenGetStatistic() throws Exception {
        this.mockMvc.perform(get("/api/v1/url/statistic"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$[0].url").value("test-site1.ru/56546/88643"))
                .andExpect(jsonPath("$[1].url").value("test-site2.ru/23123/11111"));
    }

}
