package org.library.service;

import org.library.model.Person;
import org.library.repo.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
public class PersonService {
    private final PersonRepo personRepo;

    @Autowired
    public PersonService(PersonRepo personRepo) {
        this.personRepo = personRepo;
    }

    public List<Person> findAll(){
        return personRepo.findAll();
    }

    public Person findOne(int id) {
        Optional<Person> foundPerson = personRepo.findById(id);
        return foundPerson.orElse(null);
    }

    @Transactional
    public void save(Person person) {
        personRepo.save(person);
    }

    @Transactional
    public void update(Person updatedPerson) {

        personRepo.save(updatedPerson);
    }

    @Transactional
    public void delete(int id) {
        personRepo.deleteById(id);
    }


}
