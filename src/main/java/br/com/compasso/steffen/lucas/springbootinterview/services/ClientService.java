package br.com.compasso.steffen.lucas.springbootinterview.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compasso.steffen.lucas.springbootinterview.dto.CreateClientDto;
import br.com.compasso.steffen.lucas.springbootinterview.model.City;
import br.com.compasso.steffen.lucas.springbootinterview.model.Client;
import br.com.compasso.steffen.lucas.springbootinterview.repository.CityRepository;
import javassist.NotFoundException;

@Service
public class ClientService {
  @Autowired
  CityRepository cityRepository;

  public Client createFromDto(CreateClientDto clientDto) throws NotFoundException {
    Client client = clientDto.asEntity();

    if (clientDto.getCurrentCityId() != null) {
      Optional<City> city = this.cityRepository.findById(clientDto.getCurrentCityId());

      if (!city.isPresent()) {
        throw new NotFoundException("Cidade não encontrada");
      }
    }

    return client;
  }
}
