package com.qa.hobbyprojectbackend.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class NewWeaponDTO {

	@NotBlank(message = "A weapon needs a name")
	@Size(max = 64, message = "Weapon name can have a maximum of 64 characters")
	private String name;
	
	@NotBlank(message = "A weapon needs a base weapon")
	@Size(max = 64, message = "A base weapon can have a maximum of 64 characters")
	private String base_weapon;
	
	@NotBlank(message = "A weapon needs a type")
	@Size(max = 64, message = "A weapon type can have a maximum of 64 characters")
	private String weapon_type;
	
	@Size(max = 20, message = "A rarity can have a maximum of 20 characters")
	private String rarity;
	
	@NotBlank(message = "A weapon needs a cost value")
	@Size(max = 20, message = "The weapon cost can have a maximum of 20 characters")
	private String cost;
	
	@NotBlank(message = "A weapon needs a damage amount, use the form ?d?")
	@Size(max = 20, message = "Weapon damage can have a maximum of 20 characters")
	private String damage;
	
	@NotBlank(message = "A weapon needs a damage type")
	@Size(max = 64, message = "Weapon damage type can have a maximum of 64 characters")
	private String damage_type;
	
	@Size(max = 256, message = "Weapon properties can have a maximum of 256 characters")
	private String properties;
	
	@Size(max = 512, message = "Weapon abilities can have a maximum of 512 characters")
	private String additional_abilities;
}
