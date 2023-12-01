package com.sparta.springtasticsix.springproject.controllers;

import com.sparta.springtasticsix.springproject.exceptions.duplicatelanguageprotocol.DuplicateLanguageException;
import com.sparta.springtasticsix.springproject.exceptions.languagenotfoundprotocol.LanguageNotFoundException;
import com.sparta.springtasticsix.springproject.model.entities.CountryDTO;
import com.sparta.springtasticsix.springproject.model.entities.CountrylanguageDTO;
import com.sparta.springtasticsix.springproject.model.entities.CountrylanguageIdDTO;
import com.sparta.springtasticsix.springproject.model.repositories.CountrylanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

@RestController
public class CountryLanguageController {

    private final CountrylanguageRepository countryLanguageRepository;


    @Autowired
    public CountryLanguageController(CountrylanguageRepository countryLanguageRepository) {
        this.countryLanguageRepository = countryLanguageRepository;
    }

    @PostMapping("/language/createLanguage")
    public CountrylanguageDTO createLanguage(@RequestBody CountrylanguageDTO newLanguage) throws DuplicateLanguageException {
        if (countryLanguageRepository.existsById(newLanguage.getId())){
            throw new DuplicateLanguageException(newLanguage);
        }

        countryLanguageRepository.save(newLanguage);
        return newLanguage;
    }

    @DeleteMapping("/language/deleteLanguage")
    public String deleteLanguage (@RequestParam(name = "code", required = true) String code, @RequestParam(name = "language", required = true) String language) throws LanguageNotFoundException {
        CountrylanguageIdDTO id = new CountrylanguageIdDTO();
        id.setCountryCode(code);
        id.setLanguage(language);

        Optional<CountrylanguageDTO> checkCountryLanguage = countryLanguageRepository.findById(id);

        if (checkCountryLanguage.isPresent()) {
            countryLanguageRepository.delete(checkCountryLanguage.get());

            return "Country language deleted";

        } else {

            throw new LanguageNotFoundException(language);

        }

    }

    @PatchMapping("/language/updateLanguage")
    public Optional<String> updateLanguage(@RequestParam(name = "code", required = true) String code, @RequestParam(name = "language", required = true) String language, @RequestBody Map<String, Object> updates) throws LanguageNotFoundException {
        CountrylanguageIdDTO id = new CountrylanguageIdDTO();
        id.setCountryCode(code);
        id.setLanguage(language);

        Optional<CountrylanguageDTO> checkCountryLanguage = countryLanguageRepository.findById(id);
        CountrylanguageDTO newlanguage = null;

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




    @GetMapping("/language/getByCode")
    public Optional<CountrylanguageDTO> getByCode(@RequestParam(name= "code", required = true) String code, @RequestParam(name = "language", required = true) String language) throws LanguageNotFoundException {
        CountrylanguageIdDTO id = new CountrylanguageIdDTO();
        id.setCountryCode(code);
        id.setLanguage(language);

        Optional<CountrylanguageDTO> checkCountryLanguage = countryLanguageRepository.findById(id);

        if (checkCountryLanguage.isPresent()) {
            CountrylanguageDTO countryLanguage = checkCountryLanguage.get();

            return checkCountryLanguage;
        } else {

            throw new LanguageNotFoundException(language);

        }
    }
}
