package com.yurisaito.gestore.entity;

public enum UserRole {
	ADMIN("admin"), //0
	USER("user"); //1
	
	private String role;
	
	private UserRole(String role) {
		this.role = role;
	}
	
	public String getRole() {
		return this.role;
	}
}
