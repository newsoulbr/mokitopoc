package com.github.newsoulbr.mokitopoc.service;

import com.github.newsoulbr.mokitopoc.model.Person;
import com.github.newsoulbr.mokitopoc.dto.PersonDto;
import com.github.newsoulbr.mokitopoc.repository.PersonRepository;
import com.github.newsoulbr.mokitopoc.util.EntityParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by brunoselva on 7/5/17.
 */
@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public PersonDto getUserName(Long id) {
        Person person = personRepository.findOne(id);
        if(person!=null){
            return parsePerson(person);
        }else{
            throw new RuntimeException("Person not found with id "+id);
        }

    }

    public List<PersonDto> getUserNames(){
        List<PersonDto> personDtos = new ArrayList<>();
        personRepository.findAll().forEach(person -> personDtos.add(parsePerson(person)));
        return personDtos;
    }

    private PersonDto parsePerson(Person person){
        return EntityParser.parseEntity(person);
    }

}
