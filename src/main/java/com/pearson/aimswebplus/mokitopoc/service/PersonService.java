package com.pearson.aimswebplus.mokitopoc.service;

import com.pearson.aimswebplus.mokitopoc.model.Person;
import com.pearson.aimswebplus.mokitopoc.dto.PersonDto;
import com.pearson.aimswebplus.mokitopoc.repository.PersonRepository;
import com.pearson.aimswebplus.mokitopoc.util.EntityParser;
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
       return parsePerson(personRepository.findOne(id));
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
