package com.itau.pix.dto.response;

import com.itau.pix.data.Pix;

import java.util.Date;

public class UpdateResponseDTO {

    private final Long id;
    private final String type;
    private final String value;
    private final String accountType;
    private final String account;
    private final String agency;
    private final String firstName;
    private final String lastName;
    private final Date createDate;

    public UpdateResponseDTO(Long id, String type, String value, String accountType,
                             String account, String agency, String firstName, String lastName, Date createDate) {
        this.id = id;
        this.type = type;
        this.value = value;
        this.accountType = accountType;
        this.account = account;
        this.agency = agency;
        this.firstName = firstName;
        this.lastName = lastName;
        this.createDate = createDate;
    }

    public static class Billder {
        private Long id;

        private String type;
        private String value;
        private String accountType;
        private String account;
        private String agency;
        private String firstName;
        private String lastName;
        private Date createDate;


        public UpdateResponseDTO billd() {
            return new UpdateResponseDTO(id, type, value, accountType, account, agency, firstName, lastName, createDate);
        }

        public UpdateResponseDTO billd(Pix pix) {
            this.id = pix.getId();
            this.type = pix.getType().getValue();
            this.value = pix.getValue();
            this.accountType = pix.getAccountType().getValue();
            this.account = pix.getAccount();
            this.agency = pix.getAgency();
            this.firstName = pix.getFirstName();
            this.lastName = pix.getLastName();
            this.createDate = pix.getCreateDate();
            return this.billd();
        }

        public UpdateResponseDTO.Billder id(Long id) {
            this.id = id;
            return this;
        }

        public UpdateResponseDTO.Billder type(String type) {
            this.type = type;
            return this;
        }

        public UpdateResponseDTO.Billder value(String value) {
            this.value = value;
            return this;
        }

        public UpdateResponseDTO.Billder accountType(String accountType) {
            this.accountType = accountType;
            return this;
        }

        public UpdateResponseDTO.Billder account(String account) {
            this.account = account;
            return this;
        }

        public UpdateResponseDTO.Billder agency(String agency) {
            this.agency = agency;
            return this;
        }

        public UpdateResponseDTO.Billder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UpdateResponseDTO.Billder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UpdateResponseDTO.Billder createDate(Date createDate) {
            this.createDate = createDate;
            return this;
        }

    }
}
