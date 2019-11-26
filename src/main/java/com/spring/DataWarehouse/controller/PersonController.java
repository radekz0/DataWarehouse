package com.spring.DataWarehouse.controller;

import com.spring.DataWarehouse.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @RequestMapping({"/", ""})
    public String homePage(){

        return "index";
    }

    @RequestMapping("/extract")
    public String extractPage(){
        personService.extractTest();
        return "redirect:/";
    }

    @RequestMapping("/transform")
    public String transformPage(){
        personService.transformTest();
        return "redirect:/";
    }

    @RequestMapping("/load")
    public String loadPage(){
        personService.loadTest();
        return "redirect:/";
    }


}
