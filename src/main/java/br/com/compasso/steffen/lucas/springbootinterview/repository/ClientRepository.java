package br.com.compasso.steffen.lucas.springbootinterview.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.compasso.steffen.lucas.springbootinterview.model.Client;

public interface ClientRepository extends CrudRepository<Client, Long>, ClientRepositoryCustom {
  List<Client> findByName(String name);
}
