package com.archivos.apispringbootcunoc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomeController {

        @RequestMapping("/secure")
        public String hello(){
            return "Hello World";
        }
}
