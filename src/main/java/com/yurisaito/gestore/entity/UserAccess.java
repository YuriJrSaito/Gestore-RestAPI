package com.yurisaito.gestore.entity;

import java.sql.Date;
import java.util.Collection;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.yurisaito.gestore.utils.ValidationUtil;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Entity
@Table(name = "useraccess")
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class UserAccess implements UserDetails{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@Column(unique = true)
    @NotBlank(message = "Username cannot be blank")
	@Size(min = 4, message = "Username must have at least 4 characters")
    private String username;
    
    @NotBlank(message = "Password cannot be blank")
    @Size(min = 8, message = "Password must have at least 8 characters")
	@Pattern(regexp = "^(?=.*[A-Z]).{8,}$", 
		message = "Password must have at least 8 characters and contain at least one uppercase letter")
    private String password;

	private Boolean isLogged;
	private Boolean active;
	private Date lastLogin;
	
	@Enumerated(EnumType.STRING)
	private UserRole role;
	
	public UserAccess() {
		this.id = UUID.randomUUID();
		this.active = true;
		this.isLogged = true;
	}
	
	//register
	public UserAccess(String username, String password, UserRole role) {
		this();
		this.username = username;
		this.password = password;
		this.lastLogin = null;
		this.role = role;
		validate();
	}

	private void validate(){
		ValidationUtil.validateEntity(this);
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getIsLogged() {
		return isLogged;
	}

	public void setIsLogged(Boolean isLogged) {
		this.isLogged = isLogged;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return role.getAuthorities();
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
