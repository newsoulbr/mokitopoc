package com.github.newsoulbr.mokitopoc.controller;

import com.github.newsoulbr.mokitopoc.MokitopocApplicationTests;
import com.github.newsoulbr.mokitopoc.dto.PersonDto;
import com.github.newsoulbr.mokitopoc.model.Person;
import com.github.newsoulbr.mokitopoc.repository.PersonRepository;
import com.github.newsoulbr.mokitopoc.service.PersonService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.module.mockmvc.response.MockMvcResponse;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.equalTo;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

/**
 * Created by brunoselva on 8/4/17.
 */
public class PersonControllerTest extends MokitopocApplicationTests{

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService = new PersonService();

    @Autowired
    private PersonController personController;

    private Person person;
    private Long personId;
    private List<Person> people;

    @Before
    public void setUp() {

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

        ReflectionTestUtils.setField(personController,
                "personService", personService);

        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        RestAssuredMockMvc.mockMvc(mockMvc);

    }


    @Test
    public void whenUserIdIsProvided_thenRetrievedNameIsCorrect() {
        given().when().get("/person/"+personId).then().
                statusCode(200).body("name",equalTo("John Mayer")).body("id",equalTo("32"));
    }

    @Test
    public void listAllNamesInDatabase(){

        String expected = "[{\"id\":\"1\",\"name\":\"John Mayer\"},{\"id\":\"2\",\"name\":\"Stevie Wonder\"},{\"id\":\"3\",\"name\":\"Eric Clapton\"},{\"id\":\"4\",\"name\":\"Ringo Star\"}]";

        MockMvcResponse response = given().when().get("/person/list").then().
                statusCode(200).extract().
                response();
        int sizeOfList = response.body().path("list.size()");
        List<PersonDto> list = response.getBody().path("list");
        assertThat(sizeOfList,is(4));

        assertThat(response.asString(),is(expected));

    }

}
