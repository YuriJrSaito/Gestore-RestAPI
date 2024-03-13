package com.yurisaito.gestore.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

import org.hibernate.validator.constraints.br.CPF;
import com.yurisaito.gestore.utils.ValidationUtil;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "sellers")
public class Seller implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotBlank(message = "Name cannot be empty")
    private String name;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Invalid format")
    private String email;

    @NotBlank(message = "Phone cannot be empty")
    @Pattern(regexp = "\\d{2}-\\d{5}-\\d{4}", message = "Invalid format. Use XX-XXXXX-XXXX")
    private String phone;

    @NotNull(message = "RegistrationDate cannot be null")
    private LocalDate registrationDate;

    @Column(unique = true)
    @NotBlank(message = "CPF cannot be empty")
    @CPF(message = "Invalid format")
    private String cpf;

    @NotNull(message = "isActive cannot be null")
    private Boolean isActive;

    @OneToOne(cascade = CascadeType.ALL)
    @NotNull(message = "UserAccess cannot be null")
    private UserAccess access;

    public Seller() {
        this.id = UUID.randomUUID();
        this.isActive = true;
        this.registrationDate = LocalDate.now();
    }

    public Seller(String name, String email, String phone, String cpf) {
        this();
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.cpf = cpf;
        validate();
    }

    public Seller(UUID id, String name, String email, String phone, String cpf) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.cpf = cpf;
        validate();
    }

    private void validate() {
        ValidationUtil.validateEntity(this);
    }

    public UUID getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public UserAccess getUserAccess() {
        return access;
    }

    public String getCpf() {
        return cpf;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public void setUserAccess(UserAccess userAccess) {
        this.access = userAccess;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return "Seller{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", registrationDate=" + registrationDate +
                '}';
    }
}
