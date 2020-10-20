package br.com.compasso.steffen.lucas.springbootinterview.services;

import static org.assertj.core.api.Assertions.*;

import java.util.Date;
import java.util.Optional;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.compasso.steffen.lucas.springbootinterview.dto.UpdateClientDto;
import br.com.compasso.steffen.lucas.springbootinterview.enums.SexEnum;
import br.com.compasso.steffen.lucas.springbootinterview.model.Client;
import br.com.compasso.steffen.lucas.springbootinterview.repository.ClientRepository;
import javassist.NotFoundException;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ClientServiceTest {

    @Autowired
    ClientService clientService;

    @Autowired
    ClientRepository clientRepository;

    @BeforeEach
    public void BeforeEach() {
        // por garantia
        this.clientRepository.deleteAll();
    }

    @Test
    public void shouldUpdateName() throws NotFoundException {
        Client client = new Client();

        client.setName("ronaldo");

        client = this.clientRepository.save(client);
        
        UpdateClientDto clientDto = new UpdateClientDto();
        clientDto.setName("reginaldo");

        this.clientService.updateClient(client.getId(), clientDto);

        Optional<Client> clientFound = this.clientRepository.findById(client.getId());

        assertThat(clientFound).isPresent();
        assertThat(clientFound.get().getName()).isEqualTo("reginaldo");
    }
}
