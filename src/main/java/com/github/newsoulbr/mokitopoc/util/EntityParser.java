package com.github.newsoulbr.mokitopoc.util;

import com.github.newsoulbr.mokitopoc.dto.PersonDto;
import com.github.newsoulbr.mokitopoc.model.Person;

/**
 * Created by brunoselva on 7/12/17.
 */
public class EntityParser {

    public static PersonDto parseEntity(Person person){
        return new PersonDto(person.getFirstName()+" "+person.getLastName(),person.getId().toString());
    }

}
