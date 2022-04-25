package com.qa.hobbyprojectbackend.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "character")
public class Character {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull (message = "Race cannot be null")
	@NotBlank (message = "Race cannot be empty")
	@Size(min = 3, max = 20, message = "Race needs a minimum of 3 characters and maximum of 20")
	private String race;
}
