package com.sparta.springtasticsix.springproject.model.repositories;

import com.sparta.springtasticsix.springproject.model.entities.CountryDTO;
import com.sparta.springtasticsix.springproject.model.entities.CountryLanguageDTO;
import com.sparta.springtasticsix.springproject.model.entities.CountryLanguageIdDTO;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@Repository
public interface CountryLanguageRepository extends JpaRepository<CountryLanguageDTO, CountryLanguageIdDTO> {

    Optional<CountryLanguageDTO> findById(CountryLanguageIdDTO id);

    List<CountryLanguageDTO> findOfficialLanguageByCountryCode(CountryDTO countryCode); //expected countrydto
//    Boolean isLanguageOfficial(String language);
}