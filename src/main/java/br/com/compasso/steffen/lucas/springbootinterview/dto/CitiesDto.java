package br.com.compasso.steffen.lucas.springbootinterview.dto;

import java.util.List;

import br.com.compasso.steffen.lucas.springbootinterview.model.City;

public class CitiesDto {
  private List<City> cities;

  public List<City> getCities() {
    return this.cities;
  }

  public void setCities(List<City> cities) {
    this.cities = cities;
  }
}
