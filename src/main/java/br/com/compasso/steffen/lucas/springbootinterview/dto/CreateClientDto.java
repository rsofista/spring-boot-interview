package br.com.compasso.steffen.lucas.springbootinterview.dto;

import java.sql.Date;

import br.com.compasso.steffen.lucas.springbootinterview.enums.SexEnum;
import br.com.compasso.steffen.lucas.springbootinterview.model.Client;

public class CreateClientDto {
  private String name;

  private SexEnum sex;

  private Date birthDate;

  private Long currentCityId;

  public Client asEntity() {
    Client client = new Client();

    client.setName(this.getName());
    client.setBirthDate(this.getBirthDate());

    return client;
  }

  public String getName() {
    return this.name;
  }

  public SexEnum getSex() {
    return this.sex;
  }

  public Date getBirthDate() {
    return this.birthDate;
  }

  public Long getCurrentCityId() {
    return this.currentCityId;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setSex(SexEnum sex) {
    this.sex = sex;
  }

  public void setBirthDate(Date birthDate) {
    this.birthDate = birthDate;
  }

  public void setCurrentCityId(Long currentCityId) {
    this.currentCityId = currentCityId;
  }
}
