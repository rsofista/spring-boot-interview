package br.com.compasso.steffen.lucas.springbootinterview.repository;

import java.util.List;

import br.com.compasso.steffen.lucas.springbootinterview.model.Client;

public interface ClientRepositoryCustom {
  List<Client> findByMultiple(String name, String stateName);
}
