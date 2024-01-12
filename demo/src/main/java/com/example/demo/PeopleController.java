package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/people")
public class PeopleController {

    private final PeopleService peopleService;

    @Autowired
    public PeopleController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @GetMapping
    public List<People> getAllPeople() {
        return peopleService.getAllPeople();
    }

    @GetMapping("/{id}")
    public People getPeople(@PathVariable int id) {
        return peopleService.getPeople(id);
    }

    @PostMapping
    public void addPeople(@RequestBody People people) {
        peopleService.addPeople(people);
    }

    @DeleteMapping("/{id}")
    public void deletePeople(@PathVariable int id) {
        People people = peopleService.getPeople(id);
        if (people != null) {
            peopleService.deletePeople(people);
        }
    }

    @PutMapping("/{id}")
    public void updatePeople(@PathVariable int id, @RequestBody People people) {
        People existingPeople = peopleService.getPeople(id);
        if (existingPeople != null) {
            existingPeople.setName(people.getName());
            peopleService.updatePeople(existingPeople);
        }
    }
}
