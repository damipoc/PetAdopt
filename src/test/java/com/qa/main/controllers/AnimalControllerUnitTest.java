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
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.main.domain.Animal;
import com.qa.main.services.AnimalService;

@WebMvcTest
public class AnimalControllerUnitTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private AnimalService service;

    @Test
    void createTest() throws Exception {

        Animal entry = new Animal("Type", "Name", 1, "Notes");
        String entryAsJSON = mapper.writeValueAsString(entry);

        Animal result = new Animal(2L, "Type", "Name", 1, "Notes");
        String resultAsJSON = mapper.writeValueAsString(result);

        Mockito.when(service.create(entry)).thenReturn(result);

        mvc.perform(post("/animal/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(entryAsJSON))
                .andExpect(content().json(resultAsJSON));

    }


    @Test
    public void readAllTest() throws Exception {

        List<Animal> result = new ArrayList<>();
        result.add(new Animal(1L, "Dog", "Rex", 5, "Yellow Labrador Retriever"));
        String resultAsJSON = mapper.writeValueAsString(result);

        Mockito.when(service.getAll()).thenReturn(result);

        mvc.perform(get("/animal/getAll")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(resultAsJSON));

    }

    @Test
    public void readByIDTest() throws Exception {

        Animal result = new Animal(1L, "Dog", "Rex", 5, "Yellow Labrador Retriever");
        String resultAsJSON = mapper.writeValueAsString(result);

        Mockito.when(service.getByID(1)).thenReturn(result);

        mvc.perform(get("/animal/getByID/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(resultAsJSON));

    }

    @Test
    public void updateTest() throws Exception {

        Animal result = new Animal(1L, "Dog", "Rex", 4, "Yellow Labrador Retriever");
        String resultAsJSON = mapper.writeValueAsString(result);

        Mockito.when(service.updateAnimal(1, result)).thenReturn(result);

        mvc.perform(put("/animal/update/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(resultAsJSON))
                .andExpect(content().json(resultAsJSON));

    }

    @Test
    public void deleteTest() throws Exception {

        Mockito.when(service.removeAnimal(1)).thenReturn(true);

        mvc.perform(delete("/animal/delete/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());


    }

    @Test
    public void getByTypeTest() throws Exception {

        List<Animal> result = new ArrayList<>();
        result.add(new Animal(1L, "Dog", "Rex", 5, "Yellow Labrador Retriever"));
        String resultAsJSON = mapper.writeValueAsString(result);

        Mockito.when(service.findByType("Dog")).thenReturn(result);

        mvc.perform(get("/animal/getByType/Dog")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(resultAsJSON));

    }
    
}
