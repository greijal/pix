package com.itau.pix.dto.request;

import java.util.Objects;

public class SearchRequestDTO {
    private String agency;
    private String account;
    private String firstName;
    private String lastName;
    private int page = 0;
    private int size = 10;

    public SearchRequestDTO(String agency, String account,
                            String firstName, String lastName,
                            int page, int size) {
        this.agency = agency;
        this.account = account;
        this.firstName = firstName;
        this.lastName = lastName;
        this.page = page;
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SearchRequestDTO that = (SearchRequestDTO) o;
        return page == that.page && size == that.size && Objects.equals(agency, that.agency) && Objects.equals(account, that.account) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(agency, account, firstName, lastName, page, size);
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

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }


    public static class Builder {
        private String agency;
        private String account;
        private String firstName;
        private String lastName;
        private int page = 0;
        private int size = 10;

        public SearchRequestDTO build(){
            return new SearchRequestDTO(this.agency,this.account,
                    this.firstName,this.lastName, this.page, this.size);
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

        public Builder page(int page) {
            this.page = page;
            return this;
        }

        public Builder size(int size) {
            this.size = size;
            return this;
        }
    }
}
