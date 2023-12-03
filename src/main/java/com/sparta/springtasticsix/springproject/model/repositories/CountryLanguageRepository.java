package com.sparta.springtasticsix.springproject.model.repositories;

import com.sparta.springtasticsix.springproject.model.entities.CountryDTO;
import com.sparta.springtasticsix.springproject.model.entities.CountryLanguageDTO;
import com.sparta.springtasticsix.springproject.model.entities.CountryLanguageIdDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CountryLanguageRepository extends JpaRepository<CountryLanguageDTO, CountryLanguageIdDTO> {

    Optional<CountryLanguageDTO> findById(CountryLanguageIdDTO id);

    List<CountryLanguageDTO> findOfficialLanguageByCountryCode(CountryDTO countryCode); //expected countrydto
//    Boolean isLanguageOfficial(String language);
}