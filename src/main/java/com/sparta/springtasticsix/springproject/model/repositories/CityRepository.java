package com.sparta.springtasticsix.springproject.model.repositories;

import com.sparta.springtasticsix.springproject.model.entities.CityDTO;
import com.sparta.springtasticsix.springproject.model.entities.CountryDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<CityDTO, Integer> {
    List<CityDTO> findCityDTOByCountryCode(CountryDTO country);
    List<CityDTO> findByCountryCode(CountryDTO country);
    List<CityDTO> findByOrderByCountryCode();
}
