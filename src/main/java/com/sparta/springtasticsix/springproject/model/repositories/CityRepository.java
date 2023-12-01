package com.sparta.springtasticsix.springproject.model.repositories;

import com.sparta.springtasticsix.springproject.model.entities.CityDTO;
import com.sparta.springtasticsix.springproject.model.entities.CountryDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CityRepository extends JpaRepository<CityDTO, Integer> {
    List<CityDTO> findByCountryCode(CountryDTO country);
}

