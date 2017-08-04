package com.github.newsoulbr.mokitopoc.controller;

import com.github.newsoulbr.mokitopoc.dto.PersonDto;
import com.github.newsoulbr.mokitopoc.model.Person;
import com.github.newsoulbr.mokitopoc.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by brunoselva on 8/4/17.
 */
@RequestMapping("/person")
@RestController
public class PersonController {

    @Autowired
    PersonService personService;

    @RequestMapping("/{id}")
    public PersonDto getPersonById(@PathVariable("id") Long id){
        return personService.getUserName(id);
    }

    @RequestMapping("/list")
    public List<PersonDto> getPeople(){
        return personService.getUserNames();
    }

}
