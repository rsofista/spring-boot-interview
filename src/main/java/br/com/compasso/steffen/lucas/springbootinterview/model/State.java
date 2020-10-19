package br.com.compasso.steffen.lucas.springbootinterview.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class State {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @OneToMany
  private List<City> cities;

  public Long getId() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }
}
