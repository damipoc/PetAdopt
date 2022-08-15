package com.qa.main.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Animal> create(@RequestBody Animal animal) {

        return new ResponseEntity<Animal>(service.create(animal), HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Animal>> getAll() {

        return new ResponseEntity<List<Animal>>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/getByID/{id}")
    public ResponseEntity<Animal> getByID(@PathVariable long id) {

        return new ResponseEntity<Animal>(service.getByID(id), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Animal> updateAnimal(@PathVariable long id, @RequestBody Animal animal) {

        return new ResponseEntity<Animal>(service.updateAnimal(id, animal), HttpStatus.ACCEPTED);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> removeAnimal(@PathVariable long id) {

        return new ResponseEntity<Boolean>(service.removeAnimal(id), HttpStatus.NO_CONTENT);
    }



}
