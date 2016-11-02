package com.aleksandrbogomolov.web;

import com.aleksandrbogomolov.domain.Domain;
import com.aleksandrbogomolov.repository.DeveloperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/")
public class IndexController {

    private DeveloperRepository repository;

    @Autowired
    public IndexController(DeveloperRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public String getIndex() {
        return "index";
    }

    @PostMapping(value = "/search")
    public Domain.Developer getDeveloper(@RequestBody Domain.Developer.Software soft, Model model) {
        return repository.findBySoft(soft);
//        model.addAttribute("dev", repository.findBySoft(soft));
//        return "response";
    }
}
