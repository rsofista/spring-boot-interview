package br.com.compasso.steffen.lucas.springbootinterview.controller;

import java.text.ParseException;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.compasso.steffen.lucas.springbootinterview.dto.ClientsDto;
import br.com.compasso.steffen.lucas.springbootinterview.dto.CreateClientDto;
import br.com.compasso.steffen.lucas.springbootinterview.dto.UpdateClientDto;
import br.com.compasso.steffen.lucas.springbootinterview.model.Client;
import br.com.compasso.steffen.lucas.springbootinterview.repository.ClientRepository;
import br.com.compasso.steffen.lucas.springbootinterview.services.ClientService;
import javassist.NotFoundException;

@RestController
public class ClientController {

  @Autowired
  ClientRepository clientRepository;

  @Autowired
  ClientService clientService;

  @GetMapping("/clients/{id}")
  public Optional<Client> getClientsId(@PathVariable("id") Long id) {
    return this.clientRepository.findById(id);
  }

  @GetMapping("/clients")
  public ResponseEntity<ClientsDto> getClients(CreateClientDto qry) throws ParseException {
    ClientsDto result = new ClientsDto();

    result.setClients(this.clientRepository.findByDto(qry));
    return ResponseEntity.ok().body(result);
  }

  @PostMapping("/clients")
  public ResponseEntity<Client> postClients(@RequestBody() @Valid CreateClientDto clientDto)
      throws NotFoundException, ParseException {
    return ResponseEntity.ok().body(this.clientService.createFromDto(clientDto));
  }

  @PutMapping("/clients/{id}")
  public ResponseEntity<?> putClientsId(@PathVariable("id") Long id, @RequestBody() @Valid UpdateClientDto clientDto) {
    try {
      this.clientService.updateClient(id, clientDto);
    } catch (NotFoundException e) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok().build();
  }

  @DeleteMapping("/clients/{id}")
  public ResponseEntity<?> deleteClientsId(@PathVariable("id") Long id) {
    this.clientRepository.deleteById(id);

    return ResponseEntity.ok().build();
  }
}
