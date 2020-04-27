package com.jnowlin.example.services;

import com.jnowlin.example.domain.Demographic;
import com.jnowlin.example.repositories.DemographicRepository;
import org.springframework.stereotype.Service;

@Service
public class DemographicService {

    private final DemographicRepository demographicRepository;

    public DemographicService(DemographicRepository demographicRepository) {
        this.demographicRepository = demographicRepository;
    }

    public Iterable<Demographic> getDemographics() {
        return demographicRepository.get();
    }
}
