package com.itau.pix.dto.response;

import com.itau.pix.data.Pix;

import java.io.Serializable;
import java.util.Date;


public class SearchResponseDTO implements Serializable {

    private final Long id;
    private final String value;
    private final String type;
    private final String accountType;
    private final String agency;
    private final String account;
    private final String firstName;
    private final String lastName;
    private final Date createDate;
    private final Date disabledDate;

    public SearchResponseDTO(Long id, String value, String type, String accountType,
                             String agency, String account,
                             String firstName, String lastName,
                             Date createDate, Date disabledDate) {
        this.id = id;
        this.value = value;
        this.type = type;
        this.accountType = accountType;
        this.agency = agency;
        this.account = account;
        this.firstName = firstName;
        this.lastName = lastName;
        this.createDate = createDate;
        this.disabledDate = disabledDate;
    }

    public Long getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public String getType() {
        return type;
    }

    public String getAccountType() {
        return accountType;
    }

    public String getAgency() {
        return agency;
    }

    public String getAccount() {
        return account;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Date getDisabledDate() {
        return disabledDate;
    }

    public static class Builder {
        private Long id;
        private String value;

        private String type;
        private String accountType;
        private String agency;
        private String account;
        private String firstName;
        private String lastName;
        private Date createDate;
        private Date disabledDate;


        public SearchResponseDTO of(Pix pix) {
            return id(pix.getId())
                    .type(pix.getType().getValue())
                    .value(pix.getValue())
                    .accountType(pix.getAccountType().getValue())
                    .agency(pix.getAgency())
                    .account(pix.getAccount())
                    .firstName(pix.getFirstName())
                    .lastName(pix.getLastName())
                    .createDate(pix.getCreateDate())
                    .disabledDate(pix.getDisabledDate())
                    .build();
        }

        public SearchResponseDTO build() {
            return new SearchResponseDTO(id, value, type, accountType, agency,
                    account, firstName, lastName, createDate, disabledDate);
        }


        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder value(String value) {
            this.value = value;
            return this;
        }

        public Builder type(String type) {
            this.type = type;
            return this;
        }

        public Builder accountType(String accountType) {
            this.accountType = accountType;
            return this;
        }

        public Builder agency(String agency) {
            this.agency = agency;
            return this;
        }

        public Builder account(String account) {
            this.account = account;
            return this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder createDate(Date createDate) {
            this.createDate = createDate;
            return this;
        }

        public Builder disabledDate(Date disabledDate) {
            this.disabledDate = disabledDate;
            return this;
        }


    }
}


