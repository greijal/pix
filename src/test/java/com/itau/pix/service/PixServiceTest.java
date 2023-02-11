package com.itau.pix.service;

import com.itau.pix.data.Pix;
import com.itau.pix.dto.request.CreateRequestDTO;
import com.itau.pix.dto.request.SearchRequestDTO;
import com.itau.pix.dto.request.UpdateRequestDTO;
import com.itau.pix.exception.PixAlreadyExistsException;
import com.itau.pix.exception.PixNoExistsException;
import com.itau.pix.repository.PixRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import java.util.HashMap;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PixServiceTest {

    @Mock
    private PixRepository pixRepository;
    @InjectMocks
    private PixService pixService;

    @Test
    @DisplayName("Create new pix record")
    void createTest() {
        var mock = new Pix();
        var request = new CreateRequestDTO.Builder().value("test").build();

        when(pixRepository.existsByValueAndDisabledFalse("test")).thenReturn(false);
        when(pixRepository.save(any(Pix.class))).thenReturn(mock);

        var result = pixService.create(request);
        verify(pixRepository, times(1)).save(any(Pix.class));
        assertEquals(result, mock);
    }

    @Test
    @DisplayName("Create new pix record")
    void createDuplicatedTest() {
        var request = new CreateRequestDTO.Builder().value("test").build();
        when(pixRepository.existsByValueAndDisabledFalse("test")).thenReturn(true);
        assertThrows(PixAlreadyExistsException.class, () -> pixService.create(request));
    }


    @Test
    @DisplayName("Update pix record")
    void updateTest() {
        var pix = new Pix();
        var updateRequestDTO = new UpdateRequestDTO.Billder()
                .firstName("firstName")
                .lastName("lastName")
                .account("account")
                .agency("agency")
                .accountType("corrente")
                .billd();

        when(pixRepository.findByIdAndDisabledFalse(anyLong())).thenReturn(Optional.of(pix));
        when(pixRepository.save(any(Pix.class))).thenReturn(pix);
        var result = pixService.update(1L, updateRequestDTO);

        assertEquals(result.getFirstName(), "firstName");
        assertEquals(result.getLastName(), "lastName");
        assertEquals(result.getAccount(), "account");
        assertEquals(result.getAgency(), "agency");
        assertEquals(result.getAccountType().getValue(), "CORRENTE");
    }

    @Test
    @DisplayName("Update pix record not exist")
    void updateNotExistTest() {
        when(pixRepository.findByIdAndDisabledFalse(anyLong())).thenReturn(Optional.empty());
        assertThrows(PixNoExistsException.class, () -> pixService.update(1L, new UpdateRequestDTO.Billder().billd()));
    }

    @Test
    @DisplayName("Find pix record using requestDTO")
    void findAllByRequestDTOTest() {
        var request = new SearchRequestDTO.Builder()
                .account("00000")
                .agency("0000")
                .firstName("name")
                .lastName("last")
                .page(0)
                .size(10)
                .build();

        when(pixRepository.findAll(any(Specification.class), any(PageRequest.class)))
                .thenReturn(Mockito.mock(Page.class));

        var result = pixService.findAll(request);
        assertNotNull(result);
    }

    @Test
    @DisplayName("Find pix record using empty requestDTO")
    void findAllByRequestDTOEmptyTest() {
        var request = new SearchRequestDTO.Builder()
                .build();
        when(pixRepository.findAll(any(Specification.class), any(PageRequest.class)))
                .thenReturn(Mockito.mock(Page.class));
        var result = pixService.findAll(request);
        assertNotNull(result);
    }

    @Test
    @DisplayName("Find pix record using param")
    void findAllTest() {
        var request = new HashMap<String, String>();
        request.put("a", "a");
        var pageRequest = PageRequest.of(0, 10);
        when(pixRepository.findAll(any(Specification.class), any(PageRequest.class)))
                .thenReturn(Mockito.mock(Page.class));

        var result = pixService.findAll(request, pageRequest);
        assertNotNull(result);
    }
}