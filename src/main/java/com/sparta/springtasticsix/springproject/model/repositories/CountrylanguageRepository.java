package com.sparta.springtasticsix.springproject.model.repositories;

import com.sparta.springtasticsix.springproject.model.entities.CountrylanguageDTO;
import com.sparta.springtasticsix.springproject.model.entities.CountrylanguageIdDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CountrylanguageRepository extends JpaRepository<CountrylanguageDTO, CountrylanguageIdDTO> {

    Optional<CountrylanguageDTO> findById(CountrylanguageIdDTO id);
}