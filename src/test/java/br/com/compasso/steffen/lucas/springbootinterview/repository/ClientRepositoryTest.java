package br.com.compasso.steffen.lucas.springbootinterview.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.compasso.steffen.lucas.springbootinterview.dto.CreateClientDto;
import br.com.compasso.steffen.lucas.springbootinterview.enums.SexEnum;
import br.com.compasso.steffen.lucas.springbootinterview.model.Client;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // não deu glória
public class ClientRepositoryTest {
    @Autowired
    ClientRepository clientRepository;

    @BeforeEach
    public void BeforeEach() {
        // parece não estar sendo invocado
        this.clientRepository.deleteAll();
    }
    
    @Test
    public void shouldFilterClients() throws ParseException {
        this.clientRepository.deleteAll();

        Client jhon     = this.clientRepository.save(new Client("jhon",     SexEnum.MALE,      new Date(2020, 4, 5, 45, 18, 10)));
        Client carlo    = this.clientRepository.save(new Client("carlos",   SexEnum.UNDEFINED, new Date(2020, 4, 5, 45, 18, 10)));
        Client marie    = this.clientRepository.save(new Client("marie",    SexEnum.FEMALE,    new Date(2021, 2, 5, 25, 10, 11)));
        Client catalina = this.clientRepository.save(new Client("cartlina", SexEnum.FEMALE,    new Date(2021, 2, 5, 25, 10, 11)));
        Client marcao   = this.clientRepository.save(new Client("marcão",   SexEnum.MALE,      new Date(1974, 1, 3, 12, 40, 18)));
        Client jorge    = this.clientRepository.save(new Client("jhorge",   SexEnum.UNDEFINED, new Date(1974, 1, 3, 12, 40, 18)));

        List<Client> clients = this.clientRepository.findByDto(new CreateClientDto("jhon", null, null, null));

        // hummmmmmmmmmm
        assertThat(clients).hasSize(1);
        assertThat(clients.get(0).getId()).isEqualTo(jhon.getId());
        assertThat(clients.get(0).getName()).isEqualTo(jhon.getName());

        ///---------------------------------------------------------------

        clients = this.clientRepository.findByDto(new CreateClientDto(null, SexEnum.MALE, null, null));
        
        Optional<Client> jhonFound = Arrays.stream(clients.toArray(new Client[clients.size()])).filter(c -> jhon.getId() == c.getId()).findFirst();
        Optional<Client> marcaoFound = Arrays.stream(clients.toArray(new Client[clients.size()])).filter(c -> marcao.getId() == c.getId()).findFirst();
        
        assertThat(clients).hasSize(2);
        assertThat(jhonFound).isPresent();
        assertThat(jhonFound.get().getName()).isEqualTo(jhon.getName());
        assertThat(marcaoFound).isPresent();
        assertThat(marcaoFound.get().getName()).isEqualTo(marcao.getName());
    }

    private void saveListOfClients() {
        this.clientRepository.save(new Client("jhon",     SexEnum.MALE,      new Date(2020, 4, 5, 45, 18, 10)));
        this.clientRepository.save(new Client("carlos",   SexEnum.MALE,      new Date(2020, 4, 5, 45, 18, 10)));
        this.clientRepository.save(new Client("marie",    SexEnum.FEMALE,    new Date(2021, 2, 5, 25, 10, 11)));
        this.clientRepository.save(new Client("cartlina", SexEnum.FEMALE,    new Date(2021, 2, 5, 25, 10, 11)));
        this.clientRepository.save(new Client("marcão",   SexEnum.UNDEFINED, new Date(1974, 1, 3, 12, 40, 18)));
        this.clientRepository.save(new Client("jhorge",   SexEnum.UNDEFINED, new Date(1974, 1, 3, 12, 40, 18)));
    }
}
