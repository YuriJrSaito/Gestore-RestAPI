package com.yurisaito.gestore.entity;

import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import jakarta.validation.constraints.NotBlank;
import java.util.UUID;
import com.yurisaito.gestore.utils.ValidationUtil;

@Entity
@Table(name = "category")
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@NotBlank(message = "Name cannot be blank")
	private String name;
	@NotBlank(message = "Description cannot be blank")
	private String description;

	public Category() {
		this.id = UUID.randomUUID();
	}

	public Category(String name, String description) {
		this();
		this.name = name;
		this.description = description;
		validate();
	}

	public Category(UUID id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
		validate();
	}

	private void validate() {
		ValidationUtil.validateEntity(this);
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return String.format("Category{id=%s, name='%s', description='%s'}", id, name, description);
	}
}
