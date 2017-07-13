package com.github.newsoulbr.mokitopoc.service;

import com.github.newsoulbr.mokitopoc.DbUnitTests;
import com.github.newsoulbr.mokitopoc.dto.PersonDto;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

/**
 * Created by brunoselva on 7/12/17.
 */
@DatabaseSetup(DbUnitTests.SAMPLE_DATA)
public class PersonServiceDbUnitTest extends DbUnitTests{

    @Autowired
    PersonService personService;

    @Test
    public void whenUserIdIsProvided_thenRetrievedNameIsCorrect() {
        PersonDto expected = new PersonDto("John Adams","2");
        PersonDto actual = personService.getUserName(Long.valueOf(2));
        assertThat(actual,is(expected));
    }

    @Test
    public void listAllNamesInDatabase(){

        List<PersonDto> expectedUsers = new ArrayList<>();

        expectedUsers.add(new PersonDto("George Washington","1"));
        expectedUsers.add(new PersonDto("John Adams","2"));
        expectedUsers.add(new PersonDto("Thomas Jefferson","3"));
        expectedUsers.add(new PersonDto("James Madison","4"));
        expectedUsers.add(new PersonDto("James Monroe","5"));
        expectedUsers.add(new PersonDto("John Quincy Adams","6"));
        expectedUsers.add(new PersonDto("Andrew Jackson","7"));
        expectedUsers.add(new PersonDto("Martin Van Buren","8"));

        List<PersonDto> users = personService.getUserNames();

        assertThat(expectedUsers,not(IsEmptyCollection.empty()));
        assertThat(expectedUsers.size(),is(8));
        assertThat(users,is(expectedUsers));

    }

}
