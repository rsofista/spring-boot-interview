package br.com.compasso.steffen.lucas.springbootinterview.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.compasso.steffen.lucas.springbootinterview.model.State;

public interface StateRepository extends CrudRepository<State, Long> {

}
