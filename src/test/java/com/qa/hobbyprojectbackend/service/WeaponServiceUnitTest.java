package com.qa.hobbyprojectbackend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.qa.hobbyprojectbackend.data.entity.MyCharacter;
import com.qa.hobbyprojectbackend.data.entity.Weapon;
import com.qa.hobbyprojectbackend.data.repository.WeaponRepository;
import com.qa.hobbyprojectbackend.dto.MyCharacterDTO;
import com.qa.hobbyprojectbackend.dto.WeaponDTO;

@ExtendWith(MockitoExtension.class)
public class WeaponServiceUnitTest {

	@Mock
	private WeaponRepository weaponRepository;
	
	@Mock
	private ModelMapper modelMapper;
	
	@InjectMocks
	private WeaponService weaponService;
	
	private List<MyCharacter> characters;
	private List<MyCharacterDTO> characterDTOs;
	private List<Weapon> weapons;
	private List<WeaponDTO> weaponDTOs;
	
	@BeforeEach
	public void init() {
		characters = List.of(
				new MyCharacter(1, "Teoz", "Leonine", "", "Paladin", 5, "Oath of Vengeance", "Knight"),
				new MyCharacter(2, "Maria", "Tabaxi", "", "Rogue", 5, "Swashbuckler", "Sailor"));
		characterDTOs = List.of(
				new MyCharacterDTO(1, "Teoz", "Leonine", "", "Paladin", 5, "Oath of Vengeance", "Knight"),
				new MyCharacterDTO(2, "Maria", "Tabaxi", "", "Rogue", 5, "Swashbuckler", "Sailor"));
		
		weapons = List.of(
				new Weapon(1, characters.get(0), "Manticore's Wrath", "Greataxe", "Martial Melee Weapon", "Very Rare", "30gp", "1d12 + 2", 
				"Slashing", "Heavy, Two-Handed, Ranged", "+2 to attack rolls, ranged attack launching the spikes from the axe"),
				new Weapon(2, characters.get(0), "Blackened Steel Heavy Hammer", "Maul", "Martial Melee Weapon", "Rare", "10gp", "2d6 + 1",
				"Bludgeoning", "Heavy, Two-Handed", "+1 to hit and damage, has a ground attack"));
		weaponDTOs = List.of(
				new WeaponDTO(1, characterDTOs.get(0), "Manticore's Wrath", "Greataxe", "Martial Melee Weapon", "Very Rare", "30gp", "1d12 + 2", 
				"Slashing", "Heavy, Two-Handed, Ranged", "+2 to attack rolls, ranged attack launching the spikes from the axe"),
				new WeaponDTO(2, characterDTOs.get(0), "Blackened Steel Heavy Hammer", "Maul", "Martial Melee Weapon", "Rare", "10gp", "2d6 + 1",
				"Bludgeoning", "Heavy, Two-Handed", "+1 to hit and damage, has a ground attack"));
	}
	
	@Test
	public void getAllTest() {
		// Arrange
		when(weaponRepository.findAll()).thenReturn(weapons);
		when(modelMapper.map(weapons.get(0), WeaponDTO.class)).thenReturn(weaponDTOs.get(0));
		when(modelMapper.map(weapons.get(1), WeaponDTO.class)).thenReturn(weaponDTOs.get(1));
		
		// Act
		List<WeaponDTO> actual = weaponService.getWeapons();
		
		// Assert
		assertEquals(weaponDTOs, actual);
		verify(weaponRepository).findAll();
		verify(modelMapper).map(weapons.get(0), WeaponDTO.class);
		verify(modelMapper).map(weapons.get(1), WeaponDTO.class);
	}
	
	
}
