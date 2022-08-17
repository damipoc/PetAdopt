package com.qa.main.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.qa.main.domain.Animal;
import com.qa.main.exceptions.AnimalNotFoundException;
import com.qa.main.repos.AnimalRepo;

@Service
public class AnimalService {

    private AnimalRepo repo;

    public AnimalService(AnimalRepo repo) {
        this.repo = repo;
    }

    public Animal create(Animal animal) {

        return repo.saveAndFlush(animal);
    }

    public List<Animal> getAll() {

        return repo.findAll();
    }

    public Animal getByID(long id) {

        return repo.findById(id).orElseThrow(AnimalNotFoundException::new);
    }

    public Animal updateAnimal(long id, Animal animal) {
        Animal existing = repo.findById(id).orElseThrow(AnimalNotFoundException::new);

        existing.setType(animal.getType());
        existing.setName(animal.getName());
        existing.setAge(animal.getAge());
        existing.setNotes(animal.getNotes());

        return repo.saveAndFlush(existing);
    }
    
    public Boolean removeAnimal(long id) {

        repo.deleteById(id);

        return !repo.existsById(id);
    }

    public List<Animal> findByType(String type){
        return repo.findAnimalByType(type);
    }

}
