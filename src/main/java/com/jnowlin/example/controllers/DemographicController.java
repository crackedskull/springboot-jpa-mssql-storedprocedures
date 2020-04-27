package com.jnowlin.example.controllers;

import com.jnowlin.example.domain.Demographic;
import com.jnowlin.example.services.DemographicService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demographics")
public class DemographicController {

    private final DemographicService demographicService;

    public DemographicController(DemographicService demographicService) {
        this.demographicService = demographicService;
    }

    @GetMapping
    public Iterable<Demographic> getDemographics() {
        return demographicService.getDemographics();
    }
}
