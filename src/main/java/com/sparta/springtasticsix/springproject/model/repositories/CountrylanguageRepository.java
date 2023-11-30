package com.sparta.springtasticsix.springproject.model.repositories;

import com.sparta.springtasticsix.springproject.model.entities.CountrylanguageDTO;
import com.sparta.springtasticsix.springproject.model.entities.CountrylanguageIdDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountrylanguageRepository extends JpaRepository<CountrylanguageDTO, CountrylanguageIdDTO> {
}