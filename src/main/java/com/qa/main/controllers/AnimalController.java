package com.qa.main.controllers;

import java.util.ArrayList;
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

@RestController
@RequestMapping("/animal")
public class AnimalController {
    
    private List<Animal> animals = new ArrayList<>();


    @PostMapping("/create")
    public Animal create(@RequestBody Animal animal) {
        animals.add(animal);

        return animals.get(animals.size() - 1);
    }

    @GetMapping("/getAll")
    public List<Animal> getAll() {

        return animals;
    }

    @GetMapping("/getByID/{id}")
    public Animal getByID(@PathVariable int id) {

        return animals.get(id);
    }

    @PutMapping("/update/{id}")
    public Animal updateAnimal(@PathVariable int id, @RequestBody Animal animal) {
        animals.remove(id);
        animals.add(id, animal);

        return animals.get(id);
    }
    
    @DeleteMapping("/delete/{id}")
    public Animal removeAnimal(@PathVariable int id) {

        return animals.remove(id);
    }



}
