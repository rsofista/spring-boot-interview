package br.com.compasso.steffen.lucas.springbootinterview.repository;

import java.text.ParseException;
import java.util.List;

import br.com.compasso.steffen.lucas.springbootinterview.dto.CreateClientDto;
import br.com.compasso.steffen.lucas.springbootinterview.model.Client;

public interface ClientRepositoryCustom {
  List<Client> findByDto(CreateClientDto dto) throws ParseException;
}
