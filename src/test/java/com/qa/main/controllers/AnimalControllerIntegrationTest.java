package com.qa.main.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.main.domain.Animal;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:testschema.sql", "classpath:testdata.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
public class AnimalControllerIntegrationTest {
    
    
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;


    @Test
    public void createTest() throws Exception{

        Animal entry = new Animal("Cat", "Cat name", 1, "Cat notes");
        String entryAsJSON = mapper.writeValueAsString(entry);

        Animal result = new Animal(2L, "Cat", "Cat name", 1, "Cat notes");
        String resultAsJSON = mapper.writeValueAsString(result);

        mvc.perform(post("/animal/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(entryAsJSON))
                .andExpect(content().json(resultAsJSON));

    }

    @Test
    public void readAllTest() throws Exception{

        List<Animal> result = new ArrayList<>();
        result.add(new Animal(1L, "Dog", "Rex", 5, "Yellow Labrador Retriever"));
        String resultAsJSON = mapper.writeValueAsString(result);

        mvc.perform(get("/animal/getAll")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(resultAsJSON));

    }

    @Test
    public void readByIDTest() throws Exception{

        Animal result = new Animal(1L, "Dog", "Rex", 5, "Yellow Labrador Retriever");
        String resultAsJSON = mapper.writeValueAsString(result);

        mvc.perform(get("/animal/getByID/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(resultAsJSON));

    }

    @Test
    public void updateTest() throws Exception {

        Animal result = new Animal(1L, "Dog", "Rex", 4, "Yellow Labrador Retriever");
        String resultAsJSON = mapper.writeValueAsString(result);

        mvc.perform(put("/animal/update/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(resultAsJSON))
                .andExpect(content().json(resultAsJSON));

    }

    @Test
    public void deleteTest() throws Exception {
        mvc.perform(delete("/animal/delete/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());


    }


    @Test
    public void getByTypeTest() throws Exception {

        List<Animal> result = new ArrayList<>();
        result.add(new Animal(1L, "Dog", "Rex", 5, "Yellow Labrador Retriever"));
        String resultAsJSON = mapper.writeValueAsString(result);

        mvc.perform(get("/animal/getByType/Dog")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(resultAsJSON));


    }



}
