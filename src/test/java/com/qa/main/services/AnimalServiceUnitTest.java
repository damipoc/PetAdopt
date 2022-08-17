package com.qa.main.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.main.domain.Animal;
import com.qa.main.repos.AnimalRepo;

@SpringBootTest
public class AnimalServiceUnitTest {
    
    @Autowired
    private AnimalService service;


    @MockBean
    private AnimalRepo repo;

    @Test
    public void testCreate() {

        Animal entry = new Animal("Type", "Name", 1, "Notes");

        Animal result = new Animal (2L, "Type", "Name", 1, "Notes");

        Mockito.when(repo.saveAndFlush(entry)).thenReturn(result);

        assertEquals(result, service.create(entry));

    }

    @Test
    public void testGetAll() {
        List<Animal> result = new ArrayList<>();
        result.add(new Animal(1L, "Dog", "Rex", 5, "Yellow Labrador Retriever"));

        Mockito.when(repo.findAll()).thenReturn(result);

        assertEquals(result, service.getAll());

    }

    @Test
    public void testGetByID() {
        Animal result = new Animal(1L, "Dog", "Rex", 5, "Yellow Labrador Retriever");
        Optional<Animal> resultOP = Optional.ofNullable(result);

        Mockito.when(repo.findById(1L)).thenReturn(resultOP);

        assertEquals(result, service.getByID(1L));
    }

    @Test
    public void testUpdate() {
        Animal result = new Animal(1L, "Dog", "Rex", 5, "Yellow Labrador Retriever");
        Optional<Animal> resultOP = Optional.ofNullable(result);

        Mockito.when(repo.findById(1L)).thenReturn(resultOP);

        Mockito.when(repo.saveAndFlush(result)).thenReturn(result);

        assertEquals(result, service.updateAnimal(1L, result));

    }

    @Test
    public void testDelete() {
        Mockito.when(repo.existsById(1L)).thenReturn(false);

        assertEquals(true, service.removeAnimal(1L));
    }

    @Test
    public void testfindByType() {
        List<Animal> result = new ArrayList<>();
        result.add(new Animal(1L, "Dog", "Rex", 5, "Yellow Labrador Retriever"));

        Mockito.when(repo.findAnimalByType("Dog")).thenReturn(result);

        assertEquals(result, service.findByType("Dog"));


    }


}
