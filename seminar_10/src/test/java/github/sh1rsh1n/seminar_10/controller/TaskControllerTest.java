package github.sh1rsh1n.seminar_10.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class TaskControllerTest {

    @Autowired
    TaskController taskController;

    @Autowired
    private MockMvc mockMvc;


    @Test
    void contextLoad() {
        assertThat(taskController).isNotNull();
    }

    @Sql("/tasks.sql")
    @Test
    void getAllTaskTest_isOkStatus() throws Exception {
        var requestBuilder = get("/tasks");
        this.mockMvc.perform(requestBuilder)
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON)
                );
    }

    @Test
    void getAllTaskTest_isNoContent() throws Exception {
        var requestBuilder = get("/tasks");
        this.mockMvc.perform(requestBuilder)
                .andExpectAll(
                        status().isNoContent()
                );
    }

    @Sql("/tasks.sql")
    @Test
    void getAllTaskByStatusTest_is2xxStatus() throws Exception {
        var requestBuilder = get("/tasks/TODO");
        this.mockMvc.perform(requestBuilder)
                .andExpectAll(
                        status().is2xxSuccessful()
                );
    }

    @Test
    void getAllTaskByStatusTest_isNoContent() throws Exception {
        var requestBuilder = get("/tasks/DONE");
        this.mockMvc.perform(requestBuilder)
                .andExpectAll(
                        status().isNoContent()
                );
    }

    @Test
    void addTask() {
    }

    @Test
    void updateTaskStatus() {
    }
}