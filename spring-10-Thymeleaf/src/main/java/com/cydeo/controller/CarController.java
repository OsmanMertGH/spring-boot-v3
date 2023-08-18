package com.cydeo.controller;

import com.cydeo.model.Car;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/car")
public class CarController {

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("car",new Car());

        List<Integer> yearList= Arrays.asList(2009,2010,2011,2012,2013,2014,2015,2016,2017);
        model.addAttribute("yearList", yearList);

        return "car/car-register";
    }
}
