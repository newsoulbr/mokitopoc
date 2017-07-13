package com.github.newsoulbr.mokitopoc.repository;

import com.github.newsoulbr.mokitopoc.model.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by brunoselva on 7/12/17.
 */
@Repository
public interface PersonRepository extends CrudRepository<Person,Long> {
}
