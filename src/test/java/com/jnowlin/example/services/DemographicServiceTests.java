package com.jnowlin.example.services;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.jnowlin.example.repositories.DemographicRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DemographicService.class})
public class DemographicServiceTests {

    @MockBean
    private DemographicRepository demographicRepository;

    @Autowired
    private DemographicService demographicService;

    @Test
    public void itShouldRetrieveDemographics() {

        demographicService.getDemographics();

        verify(demographicRepository, times(1)).get();
    }
}
