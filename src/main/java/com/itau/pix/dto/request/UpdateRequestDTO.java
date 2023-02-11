package com.itau.pix.dto.request;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.Objects;

public class UpdateRequestDTO implements Serializable {

    @Pattern(regexp = "^(corrente|poupanca)$", flags = {Pattern.Flag.CASE_INSENSITIVE}, message = "Invalid Account Type")
    private String accountType;
    @NotBlank
    @Digits(fraction = 0, integer = 8)
    @Size(min = 8, max = 8)
    private String account;
    @NotBlank
    @Digits(fraction = 0, integer = 4)
    @Size(min = 4, max = 4)
    private String agency;
    @NotBlank
    @Size(min = 3, max = 30)
    private String firstName;
    @Size(max = 45)
    private String lastName;

    public UpdateRequestDTO(String accountType, String account, String agency, String firstName, String lastName) {
        this.accountType = accountType;
        this.account = account;
        this.agency = agency;
        this.firstName = firstName;
        this.lastName = lastName;
    }


    public String getAccountType() {
        return accountType;
    }

    public UpdateRequestDTO setAccountType(String accountType) {
        this.accountType = accountType;
        return this;
    }

    public String getAccount() {
        return account;
    }

    public UpdateRequestDTO setAccount(String account) {
        this.account = account;
        return this;
    }

    public String getAgency() {
        return agency;
    }

    public UpdateRequestDTO setAgency(String agency) {
        this.agency = agency;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UpdateRequestDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UpdateRequestDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdateRequestDTO that = (UpdateRequestDTO) o;
        return Objects.equals(accountType, that.accountType) && Objects.equals(account, that.account) && Objects.equals(agency, that.agency) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountType, account, agency, firstName, lastName);
    }


    public static class Billder {
        private String accountType;
        private String account;
        private String agency;
        private String firstName;
        private String lastName;

        public UpdateRequestDTO billd() {
            return new UpdateRequestDTO(accountType, account, agency, firstName, lastName);

        }

        public Billder accountType(String accountType) {
            this.accountType = accountType;
            return this;
        }

        public Billder account(String account) {
            this.account = account;
            return this;
        }

        public Billder agency(String agency) {
            this.agency = agency;
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
    }
}