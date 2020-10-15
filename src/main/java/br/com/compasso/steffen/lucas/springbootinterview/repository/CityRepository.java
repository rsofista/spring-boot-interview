package br.com.compasso.steffen.lucas.springbootinterview.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.compasso.steffen.lucas.springbootinterview.model.City;

public interface CityRepository extends CrudRepository<City, Long>, CityRepositoryCustom {
  List<City> findByName(String name);
}
