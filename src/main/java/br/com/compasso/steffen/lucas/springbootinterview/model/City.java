package br.com.compasso.steffen.lucas.springbootinterview.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class City {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  private String name;

  @ManyToOne
  private State state = null;

  public Long getId() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }

  public State getState() {
    return this.state;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setState(State state) {
    this.state = state;
  }

}
