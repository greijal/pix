package com.itau.pix.data;

import com.itau.pix.data.enums.AccountType;
import com.itau.pix.data.enums.PixType;
import com.itau.pix.dto.request.CreateRequestDTO;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "pix")
@SQLDelete(sql = "UPDATE pix SET disabled=1 AND disabledDate=now() WHERE id=?")
public class Pix implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, length = 9)
    private PixType type;
    @Column(name = "value", nullable = false, unique = true, length = 77)
    private String value;
    @Enumerated(EnumType.STRING)
    @Column(name = "account_type", nullable = false, length = 10)
    private AccountType accountType;
    @Column(name = "account", nullable = false, length = 8)
    private String account;
    @Column(name = "agency", nullable = false, length = 4)
    private String agency;
    @Column(name = "first_name", nullable = false, length = 30)
    private String firstName;
    @Column(name = "last_name", nullable = true, length = 45)
    private String lastName;
    @Column(name = "disabled", nullable = false, columnDefinition = "tinyint(1) default 0")
    private boolean disabled;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "disabled_date")
    private Date disabledDate;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    private Date createDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PixType getType() {
        return type;
    }

    public void setType(PixType type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
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

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public Date getDisabledDate() {
        return disabledDate;
    }

    public void setDisabledDate(Date disabledDate) {
        this.disabledDate = disabledDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pix pix = (Pix) o;
        return disabled == pix.disabled && Objects.equals(id, pix.id) && type == pix.type && Objects.equals(value, pix.value) && accountType == pix.accountType && Objects.equals(account, pix.account) && Objects.equals(agency, pix.agency) && Objects.equals(firstName, pix.firstName) && Objects.equals(lastName, pix.lastName) && Objects.equals(disabledDate, pix.disabledDate) && Objects.equals(createDate, pix.createDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, value, accountType, account, agency, firstName, lastName, disabled, disabledDate, createDate);
    }

    public static Pix of(CreateRequestDTO requestDTO) {
        var pix = new Pix();
        pix.type = PixType.fromString(requestDTO.getType());
        pix.value = requestDTO.getValue();
        pix.accountType = AccountType.fromString(requestDTO.getAccountType());
        pix.account = requestDTO.getAccount();
        pix.agency = requestDTO.getAgency();
        pix.firstName = requestDTO.getFirstName();
        pix.lastName = requestDTO.getLastName();
        return pix;
    }


}
