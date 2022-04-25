package com.qa.hobbyprojectbackend.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "character")
public class Character {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank (message = "A character needs a race")
	@Size(min = 3, max = 20, message = "Race needs a minimum of 3 characters and maximum of 20")
	private String race;
	
	@Size(max = 64, message = "Subrace cannot exceed 64 characters")
	private String subrace;
	
	@NotBlank (message = "A character needs a class")
	@Size(min = 4, max = 20, message = "Class needs a minimum of 4 characters and a maximum of 20")
	private String character_class;
}
