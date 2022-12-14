package com.qa.main.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int age;

    private String notes;

    public Animal() {
    }

    public Animal(String type, String name, int age, String notes) {
        this.type = type;
        this.name = name;
        this.age = age;
        this.notes = notes;
    }

    public Animal(long id, String type, String name, int age, String notes) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.age = age;
        this.notes = notes;
    }

    /**
     * @return long ID of the animal
     */
    public long getId() {
        return id;
    }

    /**
     * @param id of the animal
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return String of the animal's type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type of the animal
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return String of the animal's name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name of the animal
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return int age of the animal
     */
    public int getAge() {
        return age;
    }

    /**
     * @param age of the animal in Int
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * @return String of any notes about the animal
     */
    public String getNotes() {
        return notes;
    }

    /**
     * @param notes of the animal
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + age;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((notes == null) ? 0 : notes.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Animal other = (Animal) obj;
        if (age != other.age)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (notes == null) {
            if (other.notes != null)
                return false;
        } else if (!notes.equals(other.notes))
            return false;
        if (type == null) {
            if (other.type != null)
                return false;
        } else if (!type.equals(other.type))
            return false;
        return true;
    }

}
