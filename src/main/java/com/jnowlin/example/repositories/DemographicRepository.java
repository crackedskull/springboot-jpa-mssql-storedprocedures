package com.jnowlin.example.repositories;

import com.jnowlin.example.domain.Demographic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DemographicRepository extends JpaRepository<Demographic, Long> {

    //@Procedure(name = "sp_getDemo") // I really wish @Procedure worked because @Query is ugly af
    @Query(value = "EXEC dbo.GetDemographics", nativeQuery = true)
    Iterable<Demographic> get();
}
