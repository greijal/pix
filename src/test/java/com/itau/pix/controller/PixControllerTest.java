package com.itau.pix.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itau.pix.data.Pix;
import com.itau.pix.data.enums.AccountType;
import com.itau.pix.data.enums.PixType;
import com.itau.pix.dto.request.CreateRequestDTO;
import com.itau.pix.dto.request.SearchRequestDTO;
import com.itau.pix.dto.request.UpdateRequestDTO;
import com.itau.pix.exception.PixAlreadyExistsException;
import com.itau.pix.exception.PixNoExistsException;
import com.itau.pix.service.PixService;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PixController.class)
class PixControllerTest {

    @MockBean
    private PixService service;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @ParameterizedTest
    @DisplayName("POST- create pix value CPF")
    @CsvSource({
            "cpf, 11353529459",
            "cnpj, 57814145000143",
            "celular, +5521999999999",
            "email, testeemail@gmail.com",
            "aleatorio, 387865432156787654567890987654676890",
    })
    public void createTest(String type, String value) throws Exception {
        var request = createRequestDTO();
        request.setType(type);
        request.setValue(value);
        var pix = new Pix();
        pix.setId(1L);

        when(service.create(request)).thenReturn(pix);
        mockMvc.perform(post("/pix")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("POST- create duplicated pix")
    public void createDuplicatedTest() throws Exception {
        var request = createRequestDTO();
        var ex = new PixAlreadyExistsException("test");
        when(service.create(request)).thenThrow(ex);

        mockMvc.perform(post("/pix")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(422))
                .andExpect(content().json("{\"error\":\"Pix value already exist : test\"}"));
    }

    @ParameterizedTest
    @DisplayName("POST- create pix, invalid CPF value")
    @ValueSource(strings = {"", " ", "abc", "1234", "13#45", "123.424.235-34"})
    public void createInvalidCPFTest(String cpf) throws Exception {
        var request = createRequestDTO();

        request.setType("cpf");
        request.setValue(cpf);

        mockMvc.perform(post("/pix")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(422))
                .andExpect(content().json("{\"value\":\"Invalid CPF\"}"));

    }

    @ParameterizedTest
    @DisplayName("POST- create pix, invalid CPF value")
    @ValueSource(strings = {"", " ", "abc", "1dssdf", "13#45", "342.424.234"})
    public void createInvalidCNPJTest(String cnpj) throws Exception {
        var request = createRequestDTO();

        request.setType("cnpj");
        request.setValue(cnpj);

        mockMvc.perform(post("/pix")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(422))
                .andExpect(content().json("{\"value\":\"Invalid CNPJ\"}"));

    }

    @ParameterizedTest
    @DisplayName("POST- create pix, invalid cell phone value")
    @ValueSource(strings = {"", " ", "abc", "1234", "13#45", "523333322323"})
    public void createInvalidCellPhoneTest(String cellPhone) throws Exception {
        var request = createRequestDTO();

        request.setType("celular");
        request.setValue(cellPhone);

        mockMvc.perform(post("/pix")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(422))
                .andExpect(content().json("{\"value\":\"Invalid celular\"}"));

    }


    @ParameterizedTest
    @DisplayName("POST- create pix, invalid email value")
    @ValueSource(strings = {"", " ", "abc", "asdad@", "as.com", "@.com"})
    public void createInvalidEmailTest(String email) throws Exception {
        var request = createRequestDTO();

        request.setType("email");
        request.setValue(email);

        mockMvc.perform(post("/pix")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(422))
                .andExpect(content().json("{\"value\":\"Invalid email\"}"));

    }

    @ParameterizedTest
    @DisplayName("POST- create pix, invalid random value")
    @ValueSource(strings = {"", " ", "abc", "#&$(#)#)#=@", "", "&%jdZ4G3tSMCsDnYWFoFfBiAF6njFGxU4iSd"})
    public void createInvalidRandomTest(String random) throws Exception {
        var request = createRequestDTO();

        request.setType("aleatorio");
        request.setValue(random);

        mockMvc.perform(post("/pix")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(422))
                .andExpect(content().json("{\"value\":\"Invalid aleatorio\"}"));

    }

    @ParameterizedTest
    @DisplayName("POST- create pix, invalid pix type")
    @ValueSource(strings = {"", " ", "1111", "type1"})
    public void createInvalidPixTypeTest(String type) throws Exception {
        var request = createRequestDTO();

        request.setType(type);

        mockMvc.perform(post("/pix")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(422));
    }

    @ParameterizedTest
    @DisplayName("POST- create pix, invalid account Type")
    @ValueSource(strings = {"", " ", "233", "accountType2"})
    public void createInvalidAccountTypeTest(String accountType) throws Exception {
        var request = createRequestDTO();
        request.setAccountType(accountType);
        mockMvc.perform(post("/pix")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(422));
    }

    @ParameterizedTest
    @DisplayName("POST- create pix, invalid account")
    @ValueSource(strings = {"", " ", "233", "account", "123456789"})
    public void createInvalidAccountTest(String account) throws Exception {
        var request = createRequestDTO();
        request.setAccount(account);
        mockMvc.perform(post("/pix")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(422));
    }

    @ParameterizedTest
    @DisplayName("POST- create pix, invalid agency")
    @ValueSource(strings = {"", " ", "233", "agency", "123456789"})
    public void createInvalidAgencyTest(String agency) throws Exception {
        var request = createRequestDTO();
        request.setAgency(agency);
        mockMvc.perform(post("/pix")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(422));
    }

    @ParameterizedTest
    @DisplayName("POST- create pix, invalid account")
    @ValueSource(strings = {"", " ", "as"})
    public void createInvalidNameTest(String name) throws Exception {
        var request = createRequestDTO();
        request.setFirstName(name);
        mockMvc.perform(post("/pix")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(422));
    }


    @Test
    @DisplayName("PUT- update duplicated pix")
    public void updateNoExistPixTest() throws Exception {
        var request = updateRequestDTO();
        var ex = new PixNoExistsException(1L);

        when(service.update(1L, request)).thenThrow(ex);

        mockMvc.perform(put("/pix/1")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(404))
                .andExpect(content().json("{\"error\":\"Pix ID no exist : 1\"}"));
    }


    @ParameterizedTest
    @DisplayName("PUT- update pix, invalid name")
    @ValueSource(strings = {"", " ", "as"})
    public void updateInvalidNameTest(String name) throws Exception {
        var request = updateRequestDTO();
        request.setFirstName(name);
        mockMvc.perform(put("/pix/1")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(422));
    }

    @ParameterizedTest
    @DisplayName("PUT- update pix, invalid agency")
    @ValueSource(strings = {"", " ", "233", "agency", "123456789"})
    public void updateInvalidAgencyTest(String agency) throws Exception {
        var request = updateRequestDTO();
        request.setAgency(agency);
        mockMvc.perform(put("/pix/1")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(422));
    }

    @ParameterizedTest
    @DisplayName("PUT- Update pix, invalid account Type")
    @ValueSource(strings = {"", " ", "233", "accountType2"})
    public void updateInvalidAccountTypeTest(String accountType) throws Exception {
        var request = updateRequestDTO();
        request.setAccountType(accountType);
        mockMvc.perform(put("/pix/1")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(422));
    }

    @Test
    @DisplayName("GET- get Pix by id")
    public void getPixByidTest() throws Exception {
        var pix = new Pix();
        pix.setId(1l);
        pix.setAccountType(AccountType.CORRENTE);
        pix.setType(PixType.ALEATORIO);

        when(service.findById(1l)).thenReturn(Optional.of(pix));

        mockMvc.perform(get("/pix/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(content().
                        json("    {\"id\":1,\"value\":null,\"type\":\"ALEATORIO\",\"accountType\":\"CORRENTE\",\"agency\":null,\"account\":null,\"firstName\":null,\"lastName\":null,\"createDate\":null,\"disabledDate\":null}\n\"}"));
    }

    @Test
    @DisplayName("GET- get Pix by id and not found")
    public void getPixByidNotFound() throws Exception {

        when(service.findById(1l)).thenReturn(Optional.empty());

        mockMvc.perform(get("/pix/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(404));
    }

    @Test
    @DisplayName("Get- Get PIX by query param")
    public void getPixQueryParamTest() throws Exception {

        var pix = new Pix();
        pix.setId(1l);
        pix.setAccountType(AccountType.CORRENTE);
        pix.setType(PixType.ALEATORIO);

        var param = new HashMap<String, String>();
        param.put("firstName", "a");

        var pageRequest = PageRequest.of(0, 10);

        when(service.findAll(param, pageRequest)).thenReturn(List.of(pix));

        mockMvc.perform(get("/pix/search")
                        .param("page", "0")
                        .param("size", "10")
                        .param("firstName", "a")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }

    @Test
    @DisplayName("Get- Get PIX by query param not found")
    public void getPixQueryParamNotFoundTest() throws Exception {

        var param = new HashMap<String, String>();
        param.put("firstName", "a");

        var pageRequest = PageRequest.of(0, 10);

        when(service.findAll(param, pageRequest)).thenReturn(Lists.newArrayList());

        mockMvc.perform(get("/pix/search")
                        .param("page", "0")
                        .param("size", "10")
                        .param("firstName", "a")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(404));
    }

    @Test
    @DisplayName("POST- Get PIX using DTO")
    public void getPixPostTest() throws Exception {

        var pix = new Pix();
        pix.setId(1l);
        pix.setAccountType(AccountType.CORRENTE);
        pix.setType(PixType.ALEATORIO);

        SearchRequestDTO request = new SearchRequestDTO.Builder().agency("0000").build();
        when(service.findAll(request)).thenReturn(List.of(pix));


        mockMvc.perform(post("/pix/search")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }

    @Test
    @DisplayName("POST- Get PIX using DTO not found")
    public void getPixPostNotFoundTest() throws Exception {

        SearchRequestDTO request = new SearchRequestDTO.Builder().agency("0000").build();
        when(service.findAll(any(SearchRequestDTO.class))).thenReturn(Lists.newArrayList());

        mockMvc.perform(post("/pix/search")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(404));
    }

    private static UpdateRequestDTO updateRequestDTO() {
        var bilder = new UpdateRequestDTO.Billder();
        return bilder
                .agency("1111")
                .account("00000000")
                .firstName("Nome")
                .lastName("ultimo")
                .accountType("corrente")
                .billd();
    }

    private static CreateRequestDTO createRequestDTO() {
        var bilder = new CreateRequestDTO.Builder();
        return bilder.type("cpf")
                .value("11112112345")
                .agency("1111")
                .account("00000000")
                .firstName("Nome")
                .accountType("corrente")
                .build();
    }

}