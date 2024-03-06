package com.yurisaito.gestore.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "sellers")
public class Seller implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;
    private String email;
    private String phone;
    private LocalDate registrationDate;
    @Column(unique = true)
    private String cpf;
    private Boolean isActive;

    @OneToOne(cascade = CascadeType.ALL)
    private UserAccess access;

    public Seller() {
        this.id = UUID.randomUUID();
        this.isActive = true;
    }

    public Seller(String name, String email, String phone, LocalDate registrationDate, String cpf) {
        this();
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.registrationDate = registrationDate;
        this.cpf = cpf;
    }

    public Seller(UUID id, String name, String email, String phone, LocalDate registrationDate, String cpf) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.registrationDate = registrationDate;
        this.cpf = cpf;
    }

    public UUID getID(){
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

    public String getCpf(){
        return cpf;
    }

    public Boolean getIsActive(){
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

    public void setCpf(String cpf){
        this.cpf = cpf;
    }

    public void setIsActive(boolean isActive){
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
