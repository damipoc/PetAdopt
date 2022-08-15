package com.qa.main.exceptions;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Animal not found with that ID")
public class AnimalNotFoundException extends NoSuchElementException{
    
}
