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

    /**
     * @param animal object you want to create
     * @return Animal object of the animal you created
     */
    public Animal create(Animal animal) {

        return repo.saveAndFlush(animal);
    }

    /**
     * @return List<Animal> a list of all the animal objects in the database
     */
    public List<Animal> getAll() {

        return repo.findAll();
    }

    /**
     * @param id of the animal you want to get
     * @return Animal object returned with the ID given
     */
    public Animal getByID(long id) {

        return repo.findById(id).orElseThrow(AnimalNotFoundException::new);
    }

    /**
     * @param id     of the animal you want to update
     * @param animal object with the updated details
     * @return Animal object updated with the details given
     */
    public Animal updateAnimal(long id, Animal animal) {
        Animal existing = repo.findById(id).orElseThrow(AnimalNotFoundException::new);

        existing.setType(animal.getType());
        existing.setName(animal.getName());
        existing.setAge(animal.getAge());
        existing.setNotes(animal.getNotes());

        return repo.saveAndFlush(existing);
    }

    /**
     * @param id of the animal you want to delete
     * @return Boolean false when checking if the ID exists when then turns into
     *         true confirming that the entry has been deleted
     */
    public Boolean removeAnimal(long id) {

        repo.deleteById(id);

        return !repo.existsById(id);
    }

    /**
     * @param type of the animal you want to find
     * @return List<Animal> of the animal object with the type given
     */
    public List<Animal> findByType(String type) {
        return repo.findAnimalByType(type);
    }

}
