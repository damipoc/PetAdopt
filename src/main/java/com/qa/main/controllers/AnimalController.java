package com.qa.main.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin
@RequestMapping("/animal")
public class AnimalController {

    private AnimalService service;

    public AnimalController(AnimalService service) {
        this.service = service;
    }

    /**
     * @param animal object that you want to create
     * @return ResponseEntity<Animal> 201 http response
     */
    @PostMapping("/create")
    public ResponseEntity<Animal> create(@RequestBody Animal animal) {

        return new ResponseEntity<Animal>(service.create(animal), HttpStatus.CREATED);
    }

    /**
     * @return ResponseEntity<List<Animal>> 200 http response with the list of
     *         animal objects returned
     */
    @GetMapping("/getAll")
    public ResponseEntity<List<Animal>> getAll() {

        return new ResponseEntity<List<Animal>>(service.getAll(), HttpStatus.OK);
    }

    /**
     * @param id of the animal you want to get
     * @return ResponseEntity<Animal> 200 http response with the animal object
     *         returned
     */
    @GetMapping("/getByID/{id}")
    public ResponseEntity<Animal> getByID(@PathVariable long id) {

        return new ResponseEntity<Animal>(service.getByID(id), HttpStatus.OK);
    }

    /**
     * @param id     of the animal you want to update
     * @param animal object of the new details
     * @return ResponseEntity<Animal> 202 http response confirming the update of the
     *         details in the database
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<Animal> updateAnimal(@PathVariable long id, @RequestBody Animal animal) {

        return new ResponseEntity<Animal>(service.updateAnimal(id, animal), HttpStatus.ACCEPTED);
    }

    /**
     * @param id of the animal you want to delete
     * @return ResponseEntity<Boolean> 204 http response and true boolean confirming
     *         the entry has been removed
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> removeAnimal(@PathVariable long id) {

        return new ResponseEntity<Boolean>(service.removeAnimal(id), HttpStatus.NO_CONTENT);
    }

    /**
     * @param type of the animal you want to get
     * @return List<Animal> of the animals objects you requested
     */
    @GetMapping("/getByType/{type}")
    public List<Animal> findByType(@PathVariable String type) {

        return service.findByType(type);
    }

}
