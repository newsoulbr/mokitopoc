package com.github.newsoulbr.mokitopoc.service;

import com.github.newsoulbr.mokitopoc.dto.PersonDto;
import com.github.newsoulbr.mokitopoc.MokitopocApplicationTests;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

/**
 * Created by brunoselva on 7/5/17.
 */
public class PersonServiceIntegrationTest extends MokitopocApplicationTests{

    @Autowired
    private PersonService personService;

    @Test
    public void whenUserIdIsProvided_thenRetrievedNameIsCorrect() {
        PersonDto expected = new PersonDto("Peter Gregory","1");
        PersonDto actual = personService.getUserName(Long.valueOf(1));
        assertThat(actual,is(expected));
    }

    @Test
    public void listAllNamesInDatabase(){

        List<PersonDto> expectedUsers = new ArrayList<>();

        expectedUsers.add(new PersonDto("Peter Gregory","1"));
        expectedUsers.add(new PersonDto("George Ballard","2"));
        expectedUsers.add(new PersonDto("Randolph Stevens","3"));
        expectedUsers.add(new PersonDto("William Welch","4"));
        expectedUsers.add(new PersonDto("Armando Walker","5"));
        expectedUsers.add(new PersonDto("Paul Park","6"));
        expectedUsers.add(new PersonDto("Stewart Cunningham","7"));
        expectedUsers.add(new PersonDto("Joyce Manning","8"));
        expectedUsers.add(new PersonDto("Kent Ray","9"));
        expectedUsers.add(new PersonDto("Jasmine Higgins","10"));

        List<PersonDto> users = personService.getUserNames();

        assertThat(users,not(IsEmptyCollection.empty()));
        assertThat(users.size(),is(10));
        assertThat(users,is(expectedUsers));

    }

}
