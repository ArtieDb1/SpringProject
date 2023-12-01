package com.sparta.springtasticsix.springproject.model.repositories;

import com.sparta.springtasticsix.springproject.model.entities.CityDTO;
import com.sparta.springtasticsix.springproject.model.entities.CountryDTO;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<CountryDTO, String> {

    @Query("SELECT c FROM CountryDTO c WHERE c.headOfState IS NULL OR c.headOfState = ''")
    List<CountryDTO> findCountriesWithNullOrBlankHeadOfState();

//    @Query("SELECT c.name, COUNT(ci.name) FROM CountryDTO c LEFT JOIN CityDTO ci ON c.code = ci.countryCode GROUP BY c.code ORDER BY COUNT(ci.name) DESC LIMIT 1")
//    Object findCountryWithMostCitiesNative();

}