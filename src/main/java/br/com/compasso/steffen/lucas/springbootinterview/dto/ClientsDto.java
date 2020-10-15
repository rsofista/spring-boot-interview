package br.com.compasso.steffen.lucas.springbootinterview.dto;

import java.util.List;

import br.com.compasso.steffen.lucas.springbootinterview.model.Client;

public class ClientsDto {
  private List<Client> clients;

  public List<Client> getClients() {
    return this.clients;
  }

  public void setClients(List<Client> clients) {
    this.clients = clients;
  }
}
