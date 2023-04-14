//package com.SberProjectUEN.java13springTU.REST.controller;
//
//import com.SberProjectUEN.java13springTU.REST.controller.CommonTestREST;
//import com.SberProjectUEN.java13springTU.onlinecinemaproject.dto.AddFilmDTO;
//import com.SberProjectUEN.java13springTU.onlinecinemaproject.dto.DirectorDTO;
//import com.fasterxml.jackson.core.type.TypeReference;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.MethodOrderer;
//import org.junit.jupiter.api.Order;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestMethodOrder;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import java.util.HashSet;
//import java.util.List;
//
//import static org.hamcrest.Matchers.greaterThan;
//import static org.hamcrest.Matchers.hasSize;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@Slf4j
//public class DirectorRestControllerTest
//        extends CommonTestREST {
//
//    private static Long createdDirectorID;
//
//    @Test
//    @Order(0)
//    protected void listAll() throws Exception {
//        log.info("“ест по просмотра всех режиссеров через REST начат успешно");
//        String result = mvc.perform(
//                        get("/rest/directors/getAll")
//                                .headers(headers)
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .accept(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().is2xxSuccessful())
//                .andExpect(jsonPath("$.*", hasSize(greaterThan(0))))
//                .andReturn()
//                .getResponse()
//                .getContentAsString();
//        List<DirectorDTO> directorDTOS = objectMapper.readValue(result, new TypeReference<List<DirectorDTO>>() {});
//        directorDTOS.forEach(a -> log.info(a.toString()));
//        log.info("“ест по просмотра всех режиссеров через REST закончен успешно");
//    }
//
//    @Test
//    @Order(1)
//    protected void createObject() throws Exception {
//        log.info("“ест по созданию режиссера через REST начат успешно");
//        //—оздаем нового автора дл€ создани€ через контроллер (тест дата)
//        DirectorDTO directorDTO = new DirectorDTO("REST_TestDirectorsFio", "REST_TestDirectorsPosition", false, new HashSet<>());
//
//        /*
//        ¬ызываем метод создани€ (POST) в контроллере, передаем ссылку на REST API в MOCK.
//        ¬ headers передаем токен дл€ авторизации (под админом, смотри родительский класс).
//        ќжидаем, что статус ответа будет успешным и что в ответе есть поле ID, а далее возвращаем контент как строку
//        ¬се это мы конвертируем в DirectorDTO при помощи ObjectMapper от библиотеки Jackson.
//        ѕрисваиваем в статическое поле ID созданного автора, чтобы далее с ним работать.
//         */
//        DirectorDTO result = objectMapper.readValue(mvc.perform(post("/rest/directors/add")
//                                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                                .headers(super.headers)
//                                .content(asJsonString(directorDTO))
//                                .accept(MediaType.APPLICATION_JSON_VALUE))
//                        .andExpect(status().is2xxSuccessful())
//                        .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
//                        .andReturn()
//                        .getResponse()
//                        .getContentAsString(),
//                DirectorDTO.class);
//        createdDirectorID = result.getId();
//        log.info("“ест по созданию режиссера через REST закончен успешно " + result);
//        /*
//        можно запустить один тест и по цепочке вызывать остальные:
//        updateDirector(createdDirectorID);
//         */
//    }
//
//    @Test
//    @Order(2)
//    protected void updateObject() throws Exception {
//        log.info("“ест по обновлени€ режиссера через REST начат успешно");
//        //получаем нашего автора созданного (если запускать тесты подр€д), если отдельно - создаем отдельную тест дату дл€ апдейта
//        DirectorDTO existingDirector = objectMapper.readValue(mvc.perform(get("/rest/directors/getOneById")
//                                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                                .headers(super.headers)
//                                .param("id", String.valueOf(createdDirectorID))
//                                .accept(MediaType.APPLICATION_JSON_VALUE))
//                        .andExpect(status().is2xxSuccessful())
//                        .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
//                        .andReturn()
//                        .getResponse()
//                        .getContentAsString(),
//                DirectorDTO.class);
//        //обновл€ем пол€
//        existingDirector.setDirectorsFio("REST_TestDirectorFioUPDATED");
//
//
//        //вызываем update через REST API
//        mvc.perform(put("/rest/directors/update")
//                        .contentType(MediaType.APPLICATION_JSON_VALUE)
//                        .headers(super.headers)
//                        .content(asJsonString(existingDirector))
//                        .param("id", String.valueOf(createdDirectorID))
//                        .accept(MediaType.APPLICATION_JSON_VALUE))
//                .andDo(print())
//                .andExpect(status().is2xxSuccessful());
//        log.info("“ест по обновлени€ режиссера через REST закончен успешно");
//    }
//
//    @Test
//    @Order(3)
//    void addFilm() throws Exception {
//        log.info("“ест по добавлению фильма режиссеру через REST начат успешно");
//        AddFilmDTO addFilmDTO = new AddFilmDTO(1L, createdDirectorID, 1L);
//        String result = mvc.perform(post("/rest/directors/addFilm")
//                        .contentType(MediaType.APPLICATION_JSON_VALUE)
//                        .headers(super.headers)
//                        .content(asJsonString(addFilmDTO))
//                        .accept(MediaType.APPLICATION_JSON_VALUE))
//                .andDo(print())
//                .andExpect(status().is2xxSuccessful())
//                .andReturn()
//                .getResponse()
//                .getContentAsString();
//
//        DirectorDTO director = objectMapper.readValue(result, DirectorDTO.class);
//        log.info("“ест по добавлению фильма режиссеру через REST завершен успешно с результатом {}",
//                director);
//    }
//
//    @Test
//    @Order(4)
//    protected void deleteObject() throws Exception {
//        log.info("“ест по удалению режиссера через REST начат успешно");
//        mvc.perform(deleteSoft("/rest/directors/deleteSoft/{id}", createdDirectorID)
//                        .headers(headers)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON)
//                )
//                .andDo(print())
//                .andExpect(status().is2xxSuccessful());
//        DirectorDTO existingDirector = objectMapper.readValue(mvc.perform(get("/rest/directors/getOneById")
//                                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                                .headers(super.headers)
//                                .param("id", String.valueOf(createdDirectorID))
//                                .accept(MediaType.APPLICATION_JSON_VALUE))
//                        .andExpect(status().is2xxSuccessful())
//                        .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
//                        .andReturn()
//                        .getResponse()
//                        .getContentAsString(),
//                DirectorDTO.class);
//
//        assertTrue(existingDirector.isDeleted());
//        log.info("“ест по удалению режиссера через REST завершен успешно");
//        mvc.perform(
//                        deleteSoft("/rest/directors/deleteSoft/hard/{id}", createdDirectorID)
//                                .headers(headers)
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .accept(MediaType.APPLICATION_JSON)
//                )
//                .andDo(print())
//                .andExpect(status().is2xxSuccessful());
//        log.info("ƒанные очищены");
//    }
//
//}
//
//
