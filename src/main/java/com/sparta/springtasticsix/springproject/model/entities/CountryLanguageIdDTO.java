package com.sparta.springtasticsix.springproject.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CountryLanguageIdDTO implements Serializable {
    private static final long serialVersionUID = -6736652552821672557L;
    @Size(max = 3)
    @NotNull
    @Column(name = "CountryCode", nullable = false, length = 3)
    private String countryCode;

    @Size(max = 30)
    @NotNull
    @Column(name = "Language", nullable = false, length = 30)
    private String language;

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CountryLanguageIdDTO entity = (CountryLanguageIdDTO) o;
        return Objects.equals(this.countryCode, entity.countryCode) &&
                Objects.equals(this.language, entity.language);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countryCode, language);
    }

}