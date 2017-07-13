package com.pearson.aimswebplus.mokitopoc.repository;

import com.pearson.aimswebplus.mokitopoc.model.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by brunoselva on 7/12/17.
 */
@Repository
public interface PersonRepository extends CrudRepository<Person,Long> {
}
