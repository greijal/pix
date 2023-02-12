package com.itau.pix.dto.request;

import com.itau.pix.anotation.ConditionalRegexValidation;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

@ConditionalRegexValidation(conditionalProperty = "type", value = "celular", property = "value", regex = "^\\+55[1-9]{2}(?:[2-8]|9[1-9])[0-9]{3}[0-9]{4}$", message = "Invalid celular")
@ConditionalRegexValidation(conditionalProperty = "type", value = "email", property = "value", regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$", message = "Invalid email")
@ConditionalRegexValidation(conditionalProperty = "type", value = "aleatorio", property = "value", regex = "[a-zA-Z0-9]{36,36}$", message = "Invalid aleatorio")
@ConditionalRegexValidation(conditionalProperty = "type", value = "cpf", property = "value", regex = "^\\d{3}\\d{3}\\d{3}\\d{2}$", message = "Invalid CPF")
@ConditionalRegexValidation(conditionalProperty = "type", value = "cnpj", property = "value", regex = "^\\d{2}\\d{3}\\d{3}\\d{4}\\d{2}$", message = "Invalid CNPJ")

public class CreateRequestDTO implements Serializable {
    @Pattern(regexp = "^(celular|email|cpf|cnpj|aleatorio)$", flags = {Pattern.Flag.CASE_INSENSITIVE}, message = "Invalid Pix Type")
    private String type;
    @Size(max = 77)
    private String value;
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

    public CreateRequestDTO(String type, String value, String accountType,
                            String account, String agency, String firstName,
                            String lastName) {
        this.type = type;
        this.value = value;
        this.accountType = accountType;
        this.account = account;
        this.agency = agency;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateRequestDTO that = (CreateRequestDTO) o;
        return Objects.equals(type, that.type) && Objects.equals(value, that.value) && Objects.equals(accountType, that.accountType) && Objects.equals(account, that.account) && Objects.equals(agency, that.agency) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, value, accountType, account, agency, firstName, lastName);
    }

    public static class Builder {
        private String type;
        private String value;
        private String accountType;
        private String account;
        private String agency;
        private String firstName;
        private String lastName;

        public CreateRequestDTO build() {
            return new CreateRequestDTO(type, value, accountType, account, agency, firstName, lastName);
        }

        public Builder type(String type) {
            this.type = type;
            return this;
        }

        public Builder value(String value) {
            this.value = value;
            return this;
        }

        public Builder accountType(String accountType) {
            this.accountType = accountType;
            return this;
        }

        public Builder account(String account) {
            this.account = account;
            return this;
        }

        public Builder agency(String agency) {
            this.agency = agency;
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
    }
}
