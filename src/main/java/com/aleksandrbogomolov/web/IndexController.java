package com.aleksandrbogomolov.web;

import com.aleksandrbogomolov.repository.DeveloperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/developer")
public class IndexController {

    private DeveloperRepository repository;

    @Autowired
    public IndexController(DeveloperRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(value = "/search/{soft}", method = RequestMethod.GET)
    public String getDeveloper(@PathVariable(name = "soft") String soft, Model model) {
        model.addAttribute("dev", repository.findBySoft(soft));
        return "index";
    }
}
