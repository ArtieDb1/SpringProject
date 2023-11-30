package com.sparta.springtasticsix.springproject.model.repositories;

import com.sparta.springtasticsix.springproject.model.entities.CityDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<CityDTO, Integer> {
}