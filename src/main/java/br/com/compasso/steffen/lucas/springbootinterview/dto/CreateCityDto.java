package br.com.compasso.steffen.lucas.springbootinterview.dto;

import javax.validation.constraints.NotBlank;

import br.com.compasso.steffen.lucas.springbootinterview.model.City;

public class CreateCityDto {
  @NotBlank
  private String name;

  private Long stateId;

  public City asEntity() {
    City city = new City();

    city.setName(this.name);

    return city;
  }

  public String getName() {
    return this.name;
  }

  public Long getStateId() {
    return this.stateId;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setStateId(Long stateId) {
    this.stateId = stateId;
  }
}
