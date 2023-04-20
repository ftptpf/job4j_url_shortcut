package ru.job4j.urlshortcut.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = "/db/scripts/004_dml_delete_from_table_urls.sql")
class UrlControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Sql(scripts = "/db/scripts/003_dml_insert_table_urls.sql",
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/db/scripts/004_dml_delete_from_table_urls.sql",
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @WithMockUser
    @Test
    void whenGetStatistic() throws Exception {
        this.mockMvc.perform(get("/api/v1/url/statistic"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$[0].url").value("test-site1.ru/56546/88643"))
                .andExpect(jsonPath("$[1].url").value("test-site2.ru/23123/11111"));
    }
}