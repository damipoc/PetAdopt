package com.qa.main.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.main.domain.Animal;
import com.qa.main.services.AnimalService;

@RestController
@RequestMapping("/animal")
public class AnimalController {
    
    private AnimalService service;

    public AnimalController(AnimalService service) {
        this.service = service;
    }


    @PostMapping("/create")
    public Animal create(@RequestBody Animal animal) {

        return service.create(animal);
    }

    @GetMapping("/getAll")
    public List<Animal> getAll() {

        return service.getAll();
    }

    @GetMapping("/getByID/{id}")
    public Animal getByID(@PathVariable long id) {

        return service.getByID(id);
    }

    @PutMapping("/update/{id}")
    public Animal updateAnimal(@PathVariable long id, @RequestBody Animal animal) {

        return service.updateAnimal(id, animal);
    }
    
    @DeleteMapping("/delete/{id}")
    public Boolean removeAnimal(@PathVariable long id) {

        return service.removeAnimal(id);
    }



}
