package br.com.compasso.steffen.lucas.springbootinterview.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.com.compasso.steffen.lucas.springbootinterview.enums.SexEnum;

@Entity
public class Client {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @Enumerated(EnumType.STRING)
  private SexEnum sex = SexEnum.UNDEFINED;

  private Date birthDate;

  @ManyToOne
  private City currentCity;

  public Long getId() {
    return this.id;
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

  public City getCurrentCity() {
    return this.currentCity;
  }

  public void setId(Long id) {
    this.id = id;
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

  public void setCurrentCity(City currentCity) {
    this.currentCity = currentCity;
  }
}
