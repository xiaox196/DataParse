package com.ming.data.web;

import com.ming.data.entity.Person;
import com.ming.data.service.PersonService;
import com.ming.data.utils.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author alun
 * @data 2019/12/11
 */

@RestController
@RequestMapping(value = "person")
public class PersonController {

    @Autowired
    PersonService personService;

    @GetMapping(value = "find")
    public ApiResult<List<Person>> showPersonInf(@RequestParam int count){

        return ApiResult.success(personService.getPersonInf(count));
    }

}
