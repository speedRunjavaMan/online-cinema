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
//        log.info("���� �� ��������� ���� ���������� ����� REST ����� �������");
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
//        log.info("���� �� ��������� ���� ���������� ����� REST �������� �������");
//    }
//
//    @Test
//    @Order(1)
//    protected void createObject() throws Exception {
//        log.info("���� �� �������� ��������� ����� REST ����� �������");
//        //������� ������ ������ ��� �������� ����� ���������� (���� ����)
//        DirectorDTO directorDTO = new DirectorDTO("REST_TestDirectorsFio", "REST_TestDirectorsPosition", false, new HashSet<>());
//
//        /*
//        �������� ����� �������� (POST) � �����������, �������� ������ �� REST API � MOCK.
//        � headers �������� ����� ��� ����������� (��� �������, ������ ������������ �����).
//        �������, ��� ������ ������ ����� �������� � ��� � ������ ���� ���� ID, � ����� ���������� ������� ��� ������
//        ��� ��� �� ������������ � DirectorDTO ��� ������ ObjectMapper �� ���������� Jackson.
//        ����������� � ����������� ���� ID ���������� ������, ����� ����� � ��� ��������.
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
//        log.info("���� �� �������� ��������� ����� REST �������� ������� " + result);
//        /*
//        ����� ��������� ���� ���� � �� ������� �������� ���������:
//        updateDirector(createdDirectorID);
//         */
//    }
//
//    @Test
//    @Order(2)
//    protected void updateObject() throws Exception {
//        log.info("���� �� ���������� ��������� ����� REST ����� �������");
//        //�������� ������ ������ ���������� (���� ��������� ����� ������), ���� �������� - ������� ��������� ���� ���� ��� �������
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
//        //��������� ����
//        existingDirector.setDirectorsFio("REST_TestDirectorFioUPDATED");
//
//
//        //�������� update ����� REST API
//        mvc.perform(put("/rest/directors/update")
//                        .contentType(MediaType.APPLICATION_JSON_VALUE)
//                        .headers(super.headers)
//                        .content(asJsonString(existingDirector))
//                        .param("id", String.valueOf(createdDirectorID))
//                        .accept(MediaType.APPLICATION_JSON_VALUE))
//                .andDo(print())
//                .andExpect(status().is2xxSuccessful());
//        log.info("���� �� ���������� ��������� ����� REST �������� �������");
//    }
//
//    @Test
//    @Order(3)
//    void addFilm() throws Exception {
//        log.info("���� �� ���������� ������ ��������� ����� REST ����� �������");
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
//        log.info("���� �� ���������� ������ ��������� ����� REST �������� ������� � ����������� {}",
//                director);
//    }
//
//    @Test
//    @Order(4)
//    protected void deleteObject() throws Exception {
//        log.info("���� �� �������� ��������� ����� REST ����� �������");
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
//        log.info("���� �� �������� ��������� ����� REST �������� �������");
//        mvc.perform(
//                        deleteSoft("/rest/directors/deleteSoft/hard/{id}", createdDirectorID)
//                                .headers(headers)
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .accept(MediaType.APPLICATION_JSON)
//                )
//                .andDo(print())
//                .andExpect(status().is2xxSuccessful());
//        log.info("������ �������");
//    }
//
//}
//
//
