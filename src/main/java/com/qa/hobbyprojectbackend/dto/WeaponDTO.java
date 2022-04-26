package com.qa.hobbyprojectbackend.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WeaponDTO {
	
	private int id;
	private MyCharacterDTO myCharacterDTO;
	private String name;
	private String base_weapon;
	private String weapon_type;
	private String rarity;
	private String cost;
	private String damage;
	private String damage_type;
	private String properties;
	private String additional_abilities;
}
