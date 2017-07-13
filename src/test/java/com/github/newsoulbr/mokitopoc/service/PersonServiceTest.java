package com.github.newsoulbr.mokitopoc.service;

import com.github.newsoulbr.mokitopoc.dto.PersonDto;
import com.github.newsoulbr.mokitopoc.model.Person;
import com.github.newsoulbr.mokitopoc.util.EntityParser;
import com.github.newsoulbr.mokitopoc.MokitopocApplicationTests;
import com.github.newsoulbr.mokitopoc.repository.PersonRepository;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by brunoselva on 7/5/17.
 */
public class PersonServiceTest extends MokitopocApplicationTests{

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService = new PersonService();

    private Person person;
    private Long personId;
    private List<Person> people;

    @Before
    public void setupMock() {

        MockitoAnnotations.initMocks(this);

        personId = Long.valueOf(32);
        person = new Person(personId,"John","Mayer");
        when(personRepository.findOne(personId)).thenReturn(person);

        people=new ArrayList<>();
        people.add(new Person(Long.valueOf(1),"John","Mayer"));
        people.add(new Person(Long.valueOf(2),"Stevie","Wonder"));
        people.add(new Person(Long.valueOf(3),"Eric","Clapton"));
        people.add(new Person(Long.valueOf(4),"Ringo","Star"));

        when(personRepository.findAll()).thenReturn(people);

    }

    @Test
    public void whenUserIdIsProvided_thenRetrievedNameIsCorrect() {

        PersonDto actual = personService.getUserName(personId);
        PersonDto expected = EntityParser.parseEntity(person);

        assertThat(actual,is(expected));
    }

    @Test
    public void listAllNamesInDatabase(){

        List<PersonDto> expectedUsers = new ArrayList<>();

        people.stream().forEach(person -> expectedUsers.add(EntityParser.parseEntity(person)));

        List<PersonDto> users = personService.getUserNames();

        assertThat(users,not(IsEmptyCollection.empty()));
        assertThat(users.size(),is(4));
        assertThat(users,is(expectedUsers));

    }

}
