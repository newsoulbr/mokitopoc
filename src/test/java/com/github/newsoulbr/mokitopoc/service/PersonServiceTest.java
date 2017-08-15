package com.github.newsoulbr.mokitopoc.service;

import com.github.newsoulbr.mokitopoc.dto.PersonDto;
import com.github.newsoulbr.mokitopoc.exception.PersonServiceException;
import com.github.newsoulbr.mokitopoc.model.Person;
import com.github.newsoulbr.mokitopoc.util.EntityParser;
import com.github.newsoulbr.mokitopoc.MokitopocApplicationTests;
import com.github.newsoulbr.mokitopoc.repository.PersonRepository;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.bind.annotation.ExceptionHandler;

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

        personId = 32L;
        person = new Person(personId,"John","Mayer");
        when(personRepository.findOne(personId)).thenReturn(person);

        people=new ArrayList<>();
        people.add(new Person(1L,"John","Mayer"));
        people.add(new Person(2L,"Stevie","Wonder"));
        people.add(new Person(3L,"Eric","Clapton"));
        people.add(new Person(4L,"Ringo","Star"));

        when(personRepository.findAll()).thenReturn(people);

    }

    @Test
    public void whenUserIdIsProvided_thenRetrievedNameIsCorrect() throws Exception{

        PersonDto actual = personService.getUserName(personId);
        PersonDto expected = EntityParser.parseEntity(person);

        assertThat(actual,is(expected));
    }

    @Test
    public void listAllNamesInDatabase(){

        List<PersonDto> expectedUsers = new ArrayList<>();

        people.forEach(person -> expectedUsers.add(EntityParser.parseEntity(person)));

        List<PersonDto> users = personService.getUserNames();

        assertThat(users,not(IsEmptyCollection.empty()));
        assertThat(users.size(),is(4));
        assertThat(users,is(expectedUsers));

    }

    @Test(expected= PersonServiceException.class)
    public void whenUserIdDoesNotExist() throws Exception {
        personService.getUserName(2L);
    }

    @Test(expected= PersonServiceException.class)
    public void whenUserIdisNull() throws Exception {
        personService.getUserName(null);
    }

}
