package br.com.compasso.steffen.lucas.springbootinterview.repository;

import java.util.List;

import br.com.compasso.steffen.lucas.springbootinterview.model.City;

public interface CityRepositoryCustom {
  List<City> findByMultiple(String name, String stateName);
}
