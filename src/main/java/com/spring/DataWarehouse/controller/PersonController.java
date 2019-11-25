package com.spring.DataWarehouse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PersonController {

    @RequestMapping({"/", ""})
    public String homePage(){

        return "index";
    }
}
