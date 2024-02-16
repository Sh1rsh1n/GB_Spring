package github.sh1rsh1n.seminar_10.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Проерка TaskController, с подключением к БД
 */
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

    /**
     * Проверка метода получения списка задач
     */
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

    /**
     * проверка метода получения списка всех задач
     * когда в списоке нет записей
     * @throws Exception
     */
    @Sql("/trancate.sql")
    @Test
    void getAllTaskTest_isNoContent() throws Exception {
        var requestBuilder = get("/tasks");
        this.mockMvc.perform(requestBuilder)
                .andExpectAll(
                        status().isNoContent()
                );
    }

    /**
     * Проверка метода получения списка задач, после сортировки по статусу
     * ожидаемый статус ответа 200 Ok
     * @throws Exception
     */
    @Sql("/tasks.sql")
    @Test
    void getAllTaskByStatusTest_is2xxStatus() throws Exception {
        var requestBuilder = get("/tasks/TODO");
        this.mockMvc.perform(requestBuilder)
                .andExpectAll(
                        status().is2xxSuccessful()
                );
    }

    /**
     * Проверка метода получения списка задач, после сортировки по статуса
     * ожидаемый статус ответа 204 NO_CONTENT
     * @throws Exception
     */
    @Test
    void getAllTaskByStatusTest_isNoContent() throws Exception {
        var requestBuilder = get("/tasks/DONE");
        this.mockMvc.perform(requestBuilder)
                .andExpectAll(
                        status().isNoContent()
                );
    }
}