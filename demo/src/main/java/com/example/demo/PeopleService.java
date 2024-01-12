package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeopleService {
    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<People> getAllPeople() {
        return peopleRepository.findAll();
    }

    public People getPeople(int id){
        return peopleRepository.getReferenceById(id);
    }

    public void addPeople(People people){
        peopleRepository.save(people);
    }

    public void  deletePeople(People people){
        peopleRepository.delete(people);
    }

    public void updatePeople(People people){
        peopleRepository.save(people);
    }


}
