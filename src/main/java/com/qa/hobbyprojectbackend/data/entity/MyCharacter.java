package com.qa.hobbyprojectbackend.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "character")
@Data
@AllArgsConstructor
public class MyCharacter {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank (message = "A character needs a name")
	@Size(min = 3, max = 64, message = "Name needs a minimum of 3 characters and maximum of 64")
	private String name;
	
	@NotBlank (message = "A character needs a race")
	@Size(min = 3, max = 20, message = "Race needs a minimum of 3 characters and maximum of 20")
	private String race;
	
	@Size(max = 64, message = "Subrace cannot exceed 64 characters")
	private String subrace;
	
	@Column(name = "class")
	@NotBlank (message = "A character needs a class")
	@Size(min = 4, max = 20, message = "Class needs a minimum of 4 characters and a maximum of 20")
	private String character_class;
	
	private int level;
	
	@Size(max = 64, message = "Archetype cannot exceed 64 characters")
	private String archetype;
	
	@Size(max = 64, message = "Background cannot exceed 64 characters")
	private String background;

	public MyCharacter(String name, String race, String subrace, String character_class, int level, String archetype, String background) {
		super();
		this.name = name;
		this.race = race;
		this.subrace = subrace;
		this.character_class = character_class;
		this.level = level;
		this.archetype = archetype;
		this.background = background;
	}
	
	
}
