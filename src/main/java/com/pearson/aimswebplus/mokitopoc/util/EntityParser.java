package com.pearson.aimswebplus.mokitopoc.util;

import com.pearson.aimswebplus.mokitopoc.model.Person;
import com.pearson.aimswebplus.mokitopoc.dto.PersonDto;

/**
 * Created by brunoselva on 7/12/17.
 */
public class EntityParser {

    public static PersonDto parseEntity(Person person){
        return new PersonDto(person.getFirstName()+" "+person.getLastName(),person.getId().toString());
    }

}
