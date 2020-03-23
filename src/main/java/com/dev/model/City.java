package com.dev.model;

import java.util.Objects;

public class City {
    private Long idCity;
    private Long idRegion;
    private Long idCountry;
    private String cityName;

    public City(Long idCity, Long idRegion, Long idCountry, String cityName) {
        this.idCity = idCity;
        this.idRegion = idRegion;
        this.idCountry = idCountry;
        this.cityName = cityName;
    }

    public Long getIdCity() {
        return idCity;
    }

    public void setIdCity(Long idCity) {
        this.idCity = idCity;
    }

    public Long getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(Long idRegion) {
        this.idRegion = idRegion;
    }

    public Long getIdCountry() {
        return idCountry;
    }

    public void setIdCountry(Long idCountry) {
        this.idCountry = idCountry;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return Objects.equals(idCity, city.idCity) &&
                Objects.equals(idRegion, city.idRegion) &&
                Objects.equals(idCountry, city.idCountry) &&
                Objects.equals(cityName, city.cityName);
    }

    @Override
    public int hashCode() {
        int result = 31;
        result = result + (idCity == null ? 0 : idCity.hashCode());
        result = result + (idRegion == null ? 0 : idRegion.hashCode());
        result = result + (idCountry == null ? 0 : idCountry.hashCode());
        result = result + (cityName == null ? 0 : cityName.hashCode());
        return result;
    }
}
