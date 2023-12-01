package com.sparta.springtasticsix.springproject.controllers;


import com.sparta.springtasticsix.springproject.exceptions.citynotfoundprotocol.CityNotFoundException;
import com.sparta.springtasticsix.springproject.exceptions.countrynotfoundprotocol.CountryNotFoundException;
import com.sparta.springtasticsix.springproject.exceptions.duplicatecityprotocol.DuplicateCityException;
import com.sparta.springtasticsix.springproject.exceptions.invalidcodeprotocol.InvalidCodeException;
import com.sparta.springtasticsix.springproject.model.entities.CityDTO;
import com.sparta.springtasticsix.springproject.model.entities.CountryDTO;
import com.sparta.springtasticsix.springproject.model.repositories.CityRepository;
import com.sparta.springtasticsix.springproject.model.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
public class CityController {

    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;

    @Autowired
    public CityController(CityRepository cityRepository,
                          CountryRepository countryRepository) {
        this.cityRepository = cityRepository;
        this.countryRepository = countryRepository;
    }

    @PostMapping("/city/createCity")
    public CityDTO createCity(@RequestBody CityDTO newCity) throws DuplicateCityException {
        if (cityRepository.existsById(newCity.getId())){
            throw new DuplicateCityException(newCity);
        }
        cityRepository.save(newCity);
        return newCity;
    }

    @GetMapping("/city/getById/{id}")
    public Optional<CityDTO> getByCode(@PathVariable Integer id) throws InvalidCodeException {
        Optional<CityDTO> checkCity = cityRepository.findById(id);
        if(checkCity.isPresent()) {
            return checkCity;
        } else {
//            return Optional.empty();
            throw new InvalidCodeException(id);
        }
    }

    @PatchMapping("/city/updateCity/{id}")
    public CityDTO updateCity(@PathVariable Integer id, @RequestBody CityDTO updatedCity) throws CityNotFoundException{
        CityDTO city = null;
        if(cityRepository.findById(id).isPresent()) {
            city = cityRepository.findById(id).get();
            city.setName(updatedCity.getName());
            city.setPopulation(updatedCity.getPopulation());
            city.setCountryCode(updatedCity.getCountryCode());
            city.setDistrict(updatedCity.getDistrict());
        }
        else{ throw new CityNotFoundException(id);}
        return cityRepository.save(city);
    }

    @GetMapping("/city/get5LeastPopulatedDistricts")
    public Map<String, Integer> get5LeastPopulatedDistricts(){
        List<CityDTO> allCities = cityRepository.findAll();
        Map<String, Integer> districtPopulationMap = new HashMap<>();
        districtPopulationMap.put(allCities.get(0).getDistrict(), allCities.get(0).getPopulation());

      for(int i = 1; i < allCities.size(); i++){
            if(!(allCities.get(i).getDistrict().isEmpty() || Objects.equals(allCities.get(i).getDistrict(), "-"))){

                    if (districtPopulationMap.containsKey(allCities.get(i).getDistrict())){
                        districtPopulationMap.put(allCities.get(i).getDistrict(), districtPopulationMap.get(allCities.get(i).getDistrict()) + allCities.get(i).getPopulation());
                    }
                    else{
                        districtPopulationMap.put(allCities.get(i).getDistrict(), allCities.get(i).getPopulation());
                    }
            }
        }

        Map<String, Integer> sortedDistrictPopulationMap = sortMap(districtPopulationMap);
        Map<String, Integer> districtsWithLowestPopulation = new HashMap<>();

        for (Map.Entry<String, Integer> entry : sortedDistrictPopulationMap.entrySet()) {
            if (districtsWithLowestPopulation.size() < 5) {
                districtsWithLowestPopulation.put(entry.getKey(), entry.getValue());
            }
        }
        return sortMap(districtsWithLowestPopulation);
    }

    @DeleteMapping("/city/deleteCity/{id}")
    public String deleteCity(@PathVariable Integer id) throws CityNotFoundException {
        Optional<CityDTO> checkCity = cityRepository.findById(id);
        if(checkCity.isPresent()) {
            cityRepository.delete(checkCity.get());
            return "Deleted: " + checkCity.get();
        } else {
            throw new CityNotFoundException(id);
        }
    }

    @GetMapping("/city/perCountry")
    public Integer countCitiesPerCountry(@RequestParam (name = "code", required = true ) String code) throws CountryNotFoundException {
        Optional<CountryDTO> country= countryRepository.findById(code);
        Integer counter = 0;
        if(country.isPresent()) {
            List<CityDTO> targetCities = cityRepository.findByCountryCode(country.get());

            for (CityDTO city : targetCities) {
                counter++;
            }
        } else {
            throw new CountryNotFoundException(code);
        }

        return counter;
    }

    private Map<String, Integer> sortMap(Map<String, Integer> map) {
        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue());

        Map<String, Integer> newMap = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> district : list) {
            newMap.put(district.getKey(), district.getValue());
        }
        return newMap;
    }

}
