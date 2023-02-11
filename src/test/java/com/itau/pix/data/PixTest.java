package com.itau.pix.data;

import com.itau.pix.dto.request.CreateRequestDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PixTest {

    @Test
    @DisplayName("Parse PIX Entity from request")
    void ofTest() {
        var requestDto = createRequestDTO();
        var result = Pix.of(requestDto);

        assertEquals(requestDto.getAccount(), result.getAccount());
        assertEquals(requestDto.getAgency(), result.getAgency());
        assertEquals(requestDto.getType().toUpperCase(), result.getType().getValue());
        assertEquals(requestDto.getLastName(), result.getLastName());
        assertEquals(requestDto.getFirstName(), result.getFirstName());
        assertEquals(requestDto.getAccountType().toUpperCase(), result.getAccountType().getValue());

    }

    private static CreateRequestDTO createRequestDTO() {
        var bilder = new CreateRequestDTO.Builder();
        return bilder.type("cpf")
                .value("11112112345")
                .agency("1111")
                .account("00000000")
                .firstName("Nome")
                .lastName("LastName")
                .accountType("corrente")
                .build();
    }
}