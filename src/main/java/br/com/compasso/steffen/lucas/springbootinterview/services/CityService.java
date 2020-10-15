package br.com.compasso.steffen.lucas.springbootinterview.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compasso.steffen.lucas.springbootinterview.dto.CreateCityDto;
import br.com.compasso.steffen.lucas.springbootinterview.model.City;
import br.com.compasso.steffen.lucas.springbootinterview.model.State;
import br.com.compasso.steffen.lucas.springbootinterview.repository.CityRepository;
import br.com.compasso.steffen.lucas.springbootinterview.repository.StateRepository;
import javassist.NotFoundException;

@Service
public class CityService {

  @Autowired
  private CityRepository cityRepository;

  @Autowired
  private StateRepository stateRepository;

  public City createFromDto(CreateCityDto cityDto) throws NotFoundException {
    City city = cityDto.asEntity();

    if (cityDto.getStateId() != null) {
      Optional<State> state = this.stateRepository.findById(cityDto.getStateId());

      if (!state.isPresent()) {
        throw new NotFoundException("Estado não encontrado");
      }

      city.setState(state.get());
    }

    return this.cityRepository.save(city);
  }
}
