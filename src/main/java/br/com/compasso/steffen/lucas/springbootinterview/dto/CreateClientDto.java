package br.com.compasso.steffen.lucas.springbootinterview.dto;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.validation.constraints.NotNull;

import br.com.compasso.steffen.lucas.springbootinterview.enums.SexEnum;
import br.com.compasso.steffen.lucas.springbootinterview.model.Client;

public class CreateClientDto {
  @NotNull
  private String name;

  private SexEnum sex;

  private Date birthDate;

  @NotNull
  private Long currentCityId;

  public Client asEntity() throws ParseException {
    Client client = new Client();

    client.setName(this.getName());
    client.setBirthDate(this.getBirthDate());
    client.setSex(this.sex);

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

  // public Date getBirthDateAsDate() throws ParseException {
  //   if (this.birthDate == null) {
  //     return null;
  //   }

  //   return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(this.birthDate);
  // }

  public Long getCurrentCityId() {
    return this.currentCityId;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setSex(SexEnum sex) {
    this.sex = sex;
  }

  // public void setBirthDateAsDate(Date birthDate) {
  //   this.birthDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(birthDate);
  // }

  // public void setBirthDate(Date birthDate) {
  //   this.birthDate = birthDate;
  // }
  
  public void setBirthDate(String birthDate) throws ParseException {
    this.birthDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(birthDate);
  }

  public void setCurrentCityId(Long currentCityId) {
    this.currentCityId = currentCityId;
  }
}
