package com.sparta.springtasticsix.springproject.controllers;

import com.sparta.springtasticsix.springproject.exceptions.duplicatelanguageprotocol.DuplicateLanguageException;
import com.sparta.springtasticsix.springproject.exceptions.languagenotfoundprotocol.LanguageNotFoundException;
import com.sparta.springtasticsix.springproject.model.entities.CountryDTO;
import com.sparta.springtasticsix.springproject.model.entities.CountryLanguageDTO;
import com.sparta.springtasticsix.springproject.model.entities.CountryLanguageIdDTO;
import com.sparta.springtasticsix.springproject.model.entities.CountryLanguageDTO;
import com.sparta.springtasticsix.springproject.model.repositories.CountryRepository;
import com.sparta.springtasticsix.springproject.model.repositories.CountryLanguageRepository;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;
import java.util.*;

@RestController
public class CountryLanguageController {

    private final CountryLanguageRepository countryLanguageRepository;
    private final CountryRepository countryRepository;


    @Autowired
    public CountryLanguageController(CountryLanguageRepository countryLanguageRepository, CountryRepository countryRepository) {
        this.countryLanguageRepository = countryLanguageRepository;
        this.countryRepository = countryRepository;
    }
    @Operation(summary = "Create a language by supplying a body in JSON format")
    @PostMapping("/language/createLanguage")
    public CountryLanguageDTO createLanguage(@RequestBody CountryLanguageDTO newLanguage) throws DuplicateLanguageException {
        if (countryLanguageRepository.existsById(newLanguage.getId())){
            throw new DuplicateLanguageException(newLanguage);
        }
        countryLanguageRepository.save(newLanguage);
        return newLanguage;
    }
    @Operation(summary = "Delete a language by its ID")
    @DeleteMapping("/language/deleteLanguage")
    public String deleteLanguage (@RequestParam(name = "code", required = true) String code, @RequestParam(name = "language", required = true) String language) throws LanguageNotFoundException {
        CountryLanguageIdDTO id = new CountryLanguageIdDTO();
        id.setCountryCode(code);
        id.setLanguage(language);

        Optional<CountryLanguageDTO> checkCountryLanguage = countryLanguageRepository.findById(id);

        if (checkCountryLanguage.isPresent()) {
            countryLanguageRepository.delete(checkCountryLanguage.get());

            return "Country language deleted";

        } else {

            throw new LanguageNotFoundException(language);

        }

    }
    @Operation(summary = "Update a language by supplying ID and language")
    @PatchMapping("/language/updateLanguage")
    public Optional<String> updateLanguage(@RequestParam(name = "code", required = true) String code, @RequestParam(name = "language", required = true) String language, @RequestBody Map<String, Object> updates) throws LanguageNotFoundException {
        CountryLanguageIdDTO id = new CountryLanguageIdDTO();
        id.setCountryCode(code);
        id.setLanguage(language);

        Optional<CountryLanguageDTO> checkCountryLanguage = countryLanguageRepository.findById(id);
        CountryLanguageDTO newlanguage = null;

        if (checkCountryLanguage.isPresent()) {
            return countryLanguageRepository.findById(id)
                    .map(existingLanguage -> {
                        updates.forEach((key, value) -> {
                            switch (key.toLowerCase()) {
                                case "countrycode":
                                    existingLanguage.setCountryCode((CountryDTO) value);
                                    break;
                                case "percentage":
                                    existingLanguage.setPercentage(BigDecimal.valueOf((Double) value));
                                    break;
                                case "isofficial":
                                    existingLanguage.setIsOfficial((String) value);
                                    break;
                            }
                        });
                       countryLanguageRepository.save(existingLanguage);

                        return "country updated";
                    });
        }

        else {throw new LanguageNotFoundException(language);}
    }



    @Operation(summary = "Get a language by its ID")
    @GetMapping("/language/getByCode")
    public Optional<CountryLanguageDTO> getByCode(@RequestParam(name= "code", required = true) String code, @RequestParam(name = "language", required = true) String language) throws LanguageNotFoundException {
        CountryLanguageIdDTO id = new CountryLanguageIdDTO();
        id.setCountryCode(code);
        id.setLanguage(language);

        Optional<CountryLanguageDTO> checkCountryLanguage = countryLanguageRepository.findById(id);

        if (checkCountryLanguage.isPresent()) {
            CountryLanguageDTO countryLanguage = checkCountryLanguage.get();

            return checkCountryLanguage;
        } else {

            throw new LanguageNotFoundException(language);

        }
    }

    @Operation(summary = "Get the most popular official language of a country and how many people speak it by supplying the country code")
    @GetMapping("/language/getPopulation")
    public HashMap<String, Integer> getPopulationOfOfficialLanguage(@RequestParam(name = "code", required = true)String code) {
        //exception in case there's no official language
        HashMap<String,Integer> result = new HashMap<>();
        Optional<CountryDTO> optionalCountry = countryRepository.findById(code);
        if(optionalCountry.isPresent()) {
            List<CountryLanguageDTO> officialLanguages = countryLanguageRepository.findOfficialLanguageByCountryCode(optionalCountry.get());
            String selectedLanguage = "";
            double population = 0d;
            BigDecimal largestPercentage = BigDecimal.valueOf(0);
            for(CountryLanguageDTO language : officialLanguages) {
                if(language.getIsOfficial().equals("T")) {
                    String newLanguage = language.getId().getLanguage();
                    BigDecimal percentageOfOfficialLanguage = language.getPercentage();
                    if(percentageOfOfficialLanguage.doubleValue() > largestPercentage.doubleValue()) {
                        largestPercentage = percentageOfOfficialLanguage;
                        population = optionalCountry.get().getPopulation() * (largestPercentage.doubleValue() / 100);
                        selectedLanguage = newLanguage;
                    }
                } else {
                    selectedLanguage = "This country has no official language";
                }
            }
            result.put(selectedLanguage, (int) population);
        }
        return result;
    }
}
