package com.ming.data.service;

import com.ming.data.entity.Person;

import java.util.List;

/**
 * @author alun
 * @data 2019/12/11
 */
public interface PersonService {
    List<Person> getPersonInf(int count);
}
