package com.ming.data.service.impl;

import com.ming.data.entity.Person;
import com.ming.data.service.PersonService;
import com.ming.data.utils.IdCardGenerator;
import com.ming.data.utils.PersonInfoUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author alun
 * @data 2019/12/11
 */
@Service
public class PersonServiceImpl implements PersonService {
    @Override
    public List<Person> getPersonInf(int count) {
        List<Person> persons=new ArrayList<>();
        for(int i=0;i<count;i++) {
            Person p = getPerson();
            persons.add(p);
        }
        return persons;
    }

    public Person getPerson(){
        Person person=new Person();
        person.setName(PersonInfoUtils.getChineseName());
        person.setCnid(IdCardGenerator.generate());
        person.setAddr(PersonInfoUtils.getRoad());
        person.setPhoneNumber(PersonInfoUtils.getTel());
        person.setEmail(PersonInfoUtils.getEmail(5,8));
        return person;
    }
}
