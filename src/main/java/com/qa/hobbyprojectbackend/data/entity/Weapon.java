package com.qa.hobbyprojectbackend.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "weapon")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Weapon {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "character_id", referencedColumnName = "id")
	private MyCharacter character;
	
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
	private String damageType;
	
	@Size(max = 256, message = "Weapon properties can have a maximum of 256 characters")
	private String properties;
	
	@Size(max = 512, message = "Weapon abilities can have a maximum of 512 characters")
	private String additional_abilities;
	
	
	public Weapon(String name, String base_weapon, String weapon_type, String rarity, String cost, String damage, String damageType, String properties, String additional_abilities) {
		super();
		this.name = name;
		this.base_weapon = base_weapon;
		this.weapon_type = weapon_type;
		this.rarity = rarity;
		this.cost = cost;
		this.damage = damage;
		this.damageType = damageType;
		this.properties = properties;
		this.additional_abilities = additional_abilities;
	}
	
	public Weapon(MyCharacter character, String name, String base_weapon, String weapon_type, String rarity, String cost, String damage, String damageType, String properties, String additional_abilities) {
		super();
		this.character = character;
		this.name = name;
		this.base_weapon = base_weapon;
		this.weapon_type = weapon_type;
		this.rarity = rarity;
		this.cost = cost;
		this.damage = damage;
		this.damageType = damageType;
		this.properties = properties;
		this.additional_abilities = additional_abilities;
	}
	
}
