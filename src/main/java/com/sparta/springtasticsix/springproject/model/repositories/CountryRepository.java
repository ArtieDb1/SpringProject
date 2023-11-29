package com.sparta.springtasticsix.springproject.model.repositories;

import com.sparta.springtasticsix.springproject.model.entities.CountryDTO;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<CountryDTO, String> {
}