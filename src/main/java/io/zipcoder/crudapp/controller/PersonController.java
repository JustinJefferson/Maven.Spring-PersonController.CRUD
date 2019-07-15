package io.zipcoder.crudapp.controller;

import io.zipcoder.crudapp.model.Person;
import io.zipcoder.crudapp.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/people")
public class PersonController {


    private PersonRepository repository;

    @Autowired
    public PersonController(PersonRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public Person createPerson(@RequestBody Person person) {
        return repository.save(person);
    }

    @GetMapping("/{id}")
    public Person getPerson(@PathVariable Long id) {
        return repository.findOne(id);
    }

    @GetMapping
    public List<Person> getPersonList() {
        List<Person> list = new ArrayList<>();
        repository.findAll().forEach(person -> list.add(person));
        return list;
    }

    @PutMapping("/{id}")
    public Person updatePerson(@PathVariable Long id, @RequestBody Person newPerson) {
        Person oldPerson = repository.findOne(id);
        oldPerson.setFirstName(newPerson.getFirstName());
        oldPerson.setLastName(newPerson.getLastName());
        return repository.save(oldPerson);
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable Long id) {
        repository.delete(id);
    }


}
