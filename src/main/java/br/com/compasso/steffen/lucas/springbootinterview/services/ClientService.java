package br.com.compasso.steffen.lucas.springbootinterview.services;

import java.lang.StackWalker.Option;
import java.text.ParseException;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compasso.steffen.lucas.springbootinterview.dto.CreateClientDto;
import br.com.compasso.steffen.lucas.springbootinterview.dto.UpdateClientDto;
import br.com.compasso.steffen.lucas.springbootinterview.model.City;
import br.com.compasso.steffen.lucas.springbootinterview.model.Client;
import br.com.compasso.steffen.lucas.springbootinterview.repository.CityRepository;
import br.com.compasso.steffen.lucas.springbootinterview.repository.ClientRepository;
import javassist.NotFoundException;

@Service
public class ClientService {
  @Autowired
  CityRepository cityRepository;

  @Autowired
  ClientRepository clientRepository;

  public Client createFromDto(CreateClientDto clientDto) throws NotFoundException, ParseException {
    Client client = clientDto.asEntity();

    if (clientDto.getCurrentCityId() != null) {
      Optional<City> city = this.cityRepository.findById(clientDto.getCurrentCityId());

      if (!city.isPresent()) {
        throw new NotFoundException("Cidade não encontrada");
      }

      client.setCurrentCity(city.get());
    }

    return this.clientRepository.save(client);
  }

  public void updateClient(Long id, @Valid UpdateClientDto clientDto) {
    Optional<Client> clientOpt = this.clientRepository.findById(id);

    if (clientOpt.isPresent()) {
      Client client = clientOpt.get();

      client.setName(clientDto.getName());

      this.clientRepository.save(client);
    }
  }
}
