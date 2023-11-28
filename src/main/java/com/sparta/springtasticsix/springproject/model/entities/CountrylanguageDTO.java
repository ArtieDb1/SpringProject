package com.sparta.springtasticsix.springproject.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Entity
@Table(name = "countrylanguage")
public class CountrylanguageDTO {
    @EmbeddedId
    private CountrylanguageDTOId id;

    @MapsId("countryCode")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CountryCode", nullable = false)
    private com.sparta.springtasticsix.springproject.model.entities.CountryDTO countryCode;

    @NotNull
    @Lob
    @Column(name = "IsOfficial", nullable = false)
    private String isOfficial;

    @NotNull
    @Column(name = "Percentage", nullable = false, precision = 4, scale = 1)
    private BigDecimal percentage;

    public CountrylanguageDTOId getId() {
        return id;
    }

    public void setId(CountrylanguageDTOId id) {
        this.id = id;
    }

    public com.sparta.springtasticsix.springproject.model.entities.CountryDTO getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(com.sparta.springtasticsix.springproject.model.entities.CountryDTO countryCode) {
        this.countryCode = countryCode;
    }

    public String getIsOfficial() {
        return isOfficial;
    }

    public void setIsOfficial(String isOfficial) {
        this.isOfficial = isOfficial;
    }

    public BigDecimal getPercentage() {
        return percentage;
    }

    public void setPercentage(BigDecimal percentage) {
        this.percentage = percentage;
    }

}