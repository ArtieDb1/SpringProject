package com.sparta.springtasticsix.springproject.controllers;


import com.sparta.springtasticsix.springproject.model.entities.CityDTO;
import com.sparta.springtasticsix.springproject.model.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class CityController {

    private final CityRepository cityRepository;

    @Autowired
    public CityController(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @PostMapping("/city/createCity")
    public CityDTO createCity(@RequestBody CityDTO newCity) {
        cityRepository.save(newCity);
        return newCity;
    }

    @GetMapping("/city/getById/{id}")
    public Optional<CityDTO> getByCode(@PathVariable Integer id) {
        Optional<CityDTO> checkCity = cityRepository.findById(id);
        if(checkCity.isPresent()) {
            return checkCity;
        } else {
            return Optional.empty();
        }
    }

    @PatchMapping("/city/updateCity/{id}")
    public CityDTO updateCountry(@PathVariable Integer id, @RequestBody CityDTO updatedCity) {
        CityDTO city = null;
        if(cityRepository.findById(id).isPresent()) {
            city = cityRepository.findById(id).get();
            city.setName(updatedCity.getName());
            city.setPopulation(updatedCity.getPopulation());
            city.setCountryCode(updatedCity.getCountryCode());
            city.setDistrict(updatedCity.getDistrict());
        }
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
    public String deleteCity(@PathVariable Integer id) {
        Optional<CityDTO> checkCity = cityRepository.findById(id);
        if(checkCity.isPresent()) {
            cityRepository.delete(checkCity.get());
            return "Deleted: " + checkCity.get();
        } else {
            return "City not found";
        }
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
