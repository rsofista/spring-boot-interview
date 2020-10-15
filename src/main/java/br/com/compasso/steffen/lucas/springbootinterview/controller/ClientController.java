package br.com.compasso.steffen.lucas.springbootinterview.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.compasso.steffen.lucas.springbootinterview.dto.ClientsDto;
import br.com.compasso.steffen.lucas.springbootinterview.dto.CreateClientDto;
import br.com.compasso.steffen.lucas.springbootinterview.model.Client;

@RestController
public class ClientController {

  @GetMapping("/clients")
  public ResponseEntity<ClientsDto> getClients(@RequestParam CreateClientDto qry) {
    return null;
  }
  
  @PostMapping("/clients")
  public ResponseEntity<Client> postClients(@RequestBody() @Valid CreateClientDto clientDto) {
    return ResponseEntity.ok().build();
  }
}
