package com.demo.neo4j.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author anh.nguyen
 * @created 28/02/2024
 */
@Controller
public class HomeController {

    @RequestMapping({"/home/index", "/index", "/"})
    public String index(Model model) {
        return "home/index";
    }

    @RequestMapping("/home/about")
    public String about() {
        return "home/about";
    }
}
