package br.com.compasso.steffen.lucas.springbootinterview.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.compasso.steffen.lucas.springbootinterview.dto.CitiesDto;
import br.com.compasso.steffen.lucas.springbootinterview.dto.CreateCityDto;
import br.com.compasso.steffen.lucas.springbootinterview.model.City;
import br.com.compasso.steffen.lucas.springbootinterview.repository.CityRepository;
import br.com.compasso.steffen.lucas.springbootinterview.services.CityService;
import javassist.NotFoundException;

@RestController()
public class CityController {

  @Autowired
  private CityRepository cityRepository;

  @Autowired
  private CityService cityService;

  @GetMapping("/cities")
  public CitiesDto getCities(
    @RequestParam(name = "name", required = false) String name,
    @RequestParam(name = "stateName", required = false) String stateName
  ) {
    CitiesDto result = new CitiesDto();

    result.setCities(this.cityRepository.findByMultiple(name, stateName));

    return result;
  }

  @GetMapping("/cities/{id}")
  public Optional<City> getCityById(@PathVariable("id") Long id) {
    return this.cityRepository.findById(id);
  }

  @PostMapping("/cities")
  public ResponseEntity<City> postCities(@RequestBody() @Valid CreateCityDto city) {
    try {
      return ResponseEntity.ok().body(this.cityService.createFromDto(city));
    } catch (NotFoundException e) {
      System.out.println(e.getMessage());
      return ResponseEntity.unprocessableEntity().build();
    }

  }
}
