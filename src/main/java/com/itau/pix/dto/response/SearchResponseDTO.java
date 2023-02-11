package com.itau.pix.dto.response;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.itau.pix.data.Pix;

import java.io.Serializable;
import java.util.Date;


public class SearchResponseDTO implements Serializable {

    @JsonSetter(nulls = Nulls.AS_EMPTY)
    private Long id;
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    private String type;
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    private String accountType;
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    private String agency;
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    private String account;
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    private String firstName;
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    private String lastName;
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    private Date createDate;
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    private Date disabledDate;

    public SearchResponseDTO() {
    }

    public SearchResponseDTO(Long id, String type, String accountType,
                             String agency, String account,
                             String firstName, String lastName,
                             Date createDate, Date disabledDate) {
        this.id = id;
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

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getDisabledDate() {
        return disabledDate;
    }

    public void setDisabledDate(Date disabledDate) {
        this.disabledDate = disabledDate;
    }

    public static class Billder {
        private Long id;
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
                    .accountType(pix.getAccountType().getValue())
                    .agency(pix.getAgency())
                    .account(pix.getAccount())
                    .firstName(pix.getFirstName())
                    .lastName(pix.getLastName())
                    .createDate(pix.getCreateDate())
                    .disabledDate(pix.getDisabledDate())
                    .billd();
        }

        public SearchResponseDTO billd() {
            return new SearchResponseDTO(id, type, accountType, agency,
                    account, firstName, lastName, createDate, disabledDate);
        }


        public Billder id(Long id) {
            this.id = id;
            return this;
        }

        public Billder type(String type) {
            this.type = type;
            return this;
        }

        public Billder accountType(String accountType) {
            this.accountType = accountType;
            return this;
        }

        public Billder agency(String agency) {
            this.agency = agency;
            return this;
        }

        public Billder account(String account) {
            this.account = account;
            return this;
        }

        public Billder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Billder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Billder createDate(Date createDate) {
            this.createDate = createDate;
            return this;
        }

        public Billder disabledDate(Date disabledDate) {
            this.disabledDate = disabledDate;
            return this;
        }


    }
}


