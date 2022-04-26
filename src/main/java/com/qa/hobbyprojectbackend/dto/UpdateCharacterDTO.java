package com.qa.hobbyprojectbackend.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UpdateCharacterDTO {

	@NotBlank (message = "A character needs a name")
	@Size(min = 3, max = 64, message = "Name needs a minimum of 3 characters and maximum of 64")
	private String name;
	
	@NotBlank (message = "A character needs a race")
	@Size(min = 3, max = 20, message = "Race needs a minimum of 3 characters and maximum of 20")
	private String race;
	
	@Size(max = 64, message = "Subrace cannot exceed 64 characters")
	private String subrace;
	
	@NotBlank (message = "A character needs a class")
	@Size(min = 4, max = 20, message = "Class needs a minimum of 4 characters and a maximum of 20")
	private String character_class;
	
	private int level;
	
	@Size(max = 64, message = "Archetype cannot exceed 64 characters")
	private String archetype;
	
	@Size(max = 64, message = "Background cannot exceed 64 characters")
	private String background;
}
