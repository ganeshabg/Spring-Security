package com.example.springsecuritydatabase.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {



    @GetMapping("/user")
    @PreAuthorize("hasPermission('hasAccess','USER')")
    public String getName(){
        return "Test Name";
    }


}
