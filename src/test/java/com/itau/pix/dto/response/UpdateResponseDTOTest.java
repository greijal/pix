package com.itau.pix.dto.response;

import com.itau.pix.data.Pix;
import com.itau.pix.data.enums.AccountType;
import com.itau.pix.data.enums.PixType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UpdateResponseDTOTest {

    @Test
    @DisplayName("Create response DTO from PIX data")
    void ofTest() {
        var pix = new Pix();
        pix.setId(10L);
        pix.setType(PixType.ALEATORIO);
        pix.setValue("value");
        pix.setAccountType(AccountType.CORRENTE);
        pix.setAccount("0000");
        pix.setAccount("00000000");
        pix.setFirstName("name");
        pix.setLastName("last");
        pix.setDisabledDate(new Date());
        pix.setCreateDate(new Date());

        var result = new UpdateResponseDTO.Builder().of(pix);

        assertEquals(pix.getId(), result.getId());
        assertEquals(pix.getValue(), result.getValue());
        assertEquals(pix.getType().getValue(), result.getType());
        assertEquals(pix.getAccountType().getValue(), result.getAccountType());
        assertEquals(pix.getAgency(), result.getAgency());
        assertEquals(pix.getAccount(), result.getAccount());
        assertEquals(pix.getFirstName(), result.getFirstName());
        assertEquals(pix.getLastName(), result.getLastName());
        assertEquals(pix.getCreateDate(), result.getCreateDate());

    }

}