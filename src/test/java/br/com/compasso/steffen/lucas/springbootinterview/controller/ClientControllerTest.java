package br.com.compasso.steffen.lucas.springbootinterview.controller;

import java.net.URI;
import java.net.URISyntaxException;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.GsonFactoryBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import br.com.compasso.steffen.lucas.springbootinterview.model.Client;
import br.com.compasso.steffen.lucas.springbootinterview.repository.ClientRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ClientControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ClientRepository clientRepository;

	private String token;

    @Test
    public void shouldDelete() throws Exception {
        this.getToken();

        Client client = this.clientRepository.save(new Client());
        URI uri = new URI("/clients/"+client.getId());

        this.mockMvc.perform(MockMvcRequestBuilders.delete(uri).header(HttpHeaders.AUTHORIZATION, "Bearer "+this.token)).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void shouldNotDelete() throws Exception {
        this.getToken();

        URI uri = new URI("/clients/1");

        this.mockMvc.perform(MockMvcRequestBuilders.delete(uri).header(HttpHeaders.AUTHORIZATION, "Bearer "+this.token)).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    private void getToken() throws Exception {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("username", "lucas");
        jsonObject.put("password", "123456");

        MvcResult result = this.mockMvc.perform(
            MockMvcRequestBuilders
                .post(new URI("/auth"))
                .content(jsonObject.toString())
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn();

        String resultBody = result.getResponse().getContentAsString();
        
        JSONObject authObject = new JSONObject(resultBody);

        this.token = authObject.getString("token");
    }
}
