package com.qa.hobbyprojectbackend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Assertions;
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
import com.qa.hobbyprojectbackend.dto.NewWeaponDTO;
import com.qa.hobbyprojectbackend.dto.UpdateCharacterDTO;
import com.qa.hobbyprojectbackend.dto.UpdateWeaponDTO;
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
	
	@Test
	public void getByCharacterTest() {
		// Arrange
		Weapon weapon = weapons.get(0);
		int characterId = weapon.getCharacter().getId();
		
		when(weaponRepository.findByCharacterId(characterId)).thenReturn(weapons);
		when(modelMapper.map(weapons.get(0), WeaponDTO.class)).thenReturn(weaponDTOs.get(0));
		when(modelMapper.map(weapons.get(1), WeaponDTO.class)).thenReturn(weaponDTOs.get(1));
		
		// Act
		List<WeaponDTO> actual = weaponService.getWeaponsByCharacterId(characterId);
		
		// Assert
		assertEquals(weaponDTOs, actual);
		verify(weaponRepository).findByCharacterId(characterId);
		verify(modelMapper).map(weapons.get(0), WeaponDTO.class);
		verify(modelMapper).map(weapons.get(1), WeaponDTO.class);
	}
	
	@Test
	public void getByIdTest() {
		// Arrange
		Weapon weapon = weapons.get(0);
		WeaponDTO weaponDTO = weaponDTOs.get(0);
		int id = weapon.getId();
		
		when(weaponRepository.findById(id)).thenReturn(Optional.of(weapon));
		when(modelMapper.map(weapon, WeaponDTO.class)).thenReturn(weaponDTO);
		
		// Act
		WeaponDTO actual = weaponService.getWeapon(id);
		
		// Assert
		assertEquals(weaponDTO, actual);
		verify(weaponRepository).findById(id);
		verify(modelMapper).map(weapon, WeaponDTO.class);
	}
	
	@Test
	public void getByInvalidId() {
		// Arrange
		int id = 555;
		when(weaponRepository.findById(id)).thenReturn(Optional.empty());
		
		// Act
		EntityNotFoundException exception = Assertions.assertThrows(EntityNotFoundException.class, () -> {
			weaponService.getWeapon(id);
		});
		
		// Assert
		String expectedMessage = "Weapon not found with id " + id;
		assertEquals(expectedMessage, exception.getMessage());
		verify(weaponRepository).findById(id);
	}
	
	@Test
	public void createTest() {
		// Arrange
		MyCharacterDTO characterDTO = characterDTOs.get(0);
		Weapon weapon = weapons.get(0);
		Weapon newWeapon = new Weapon(1, weapon.getCharacter(), weapon.getName(), weapon.getBase_weapon(), weapon.getWeapon_type(), weapon.getRarity(), 
				weapon.getCost(), weapon.getDamage(), weapon.getDamage_type(), weapon.getProperties(), weapon.getAdditional_abilities());
		WeaponDTO expected = weaponDTOs.get(0);
		
		NewWeaponDTO weaponDTO = new NewWeaponDTO(weapon.getName(), weapon.getBase_weapon(), weapon.getWeapon_type(), weapon.getRarity(), 
				weapon.getCost(), weapon.getDamage(), weapon.getDamage_type(), weapon.getProperties(), weapon.getAdditional_abilities());
		WeaponDTO createdWeaponDTO = new WeaponDTO(1, characterDTO, weapon.getName(), weapon.getBase_weapon(), weapon.getWeapon_type(), weapon.getRarity(), 
				weapon.getCost(), weapon.getDamage(), weapon.getDamage_type(), weapon.getProperties(), weapon.getAdditional_abilities());
		
		when(modelMapper.map(weaponDTO, Weapon.class)).thenReturn(newWeapon);
		when(weaponRepository.save(newWeapon)).thenReturn(weapon);
		when(modelMapper.map(weapon, WeaponDTO.class)).thenReturn(createdWeaponDTO);
		
		// Act
		WeaponDTO actual = weaponService.createWeapon(weaponDTO);
		
		// Assert
		assertEquals(expected, actual);
		verify(modelMapper).map(weaponDTO, Weapon.class);
		verify(weaponRepository).save(newWeapon);
		verify(modelMapper).map(weapon, WeaponDTO.class);
	}
	
	@Test
	public void deleteTest() {
		// Arrange
		int id = 1;
		when(weaponRepository.existsById(id)).thenReturn(true);
		
		// Act
		weaponService.deleteWeapon(id);
		
		// Assert
		verify(weaponRepository).existsById(id);
	}
	
	@Test
	public void invalidIdDeleteTest() {
		// Arrange
		int id = 417;
		when(weaponRepository.existsById(id)).thenReturn(false);
		
		// Act
		EntityNotFoundException exception = Assertions.assertThrows(EntityNotFoundException.class, () -> {
			weaponService.deleteWeapon(id);
		});
		
		// Assert
		String expectedMessage = "Weapon not found with id " + id;
		assertEquals(expectedMessage, exception.getMessage());
		verify(weaponRepository).existsById(id);
	}
	
	@Test
	public void updateTest() {
		// Arrange
		Weapon weapon = weapons.get(1);
		int id = weapon.getId();
		MyCharacterDTO characterDTO = characterDTOs.get(1);
		
		UpdateWeaponDTO weaponDTO = new UpdateWeaponDTO();
		weaponDTO.setName(weaponDTO.getName());
		weaponDTO.setBase_weapon(weaponDTO.getBase_weapon());
		weaponDTO.setWeapon_type(weaponDTO.getWeapon_type());
		weaponDTO.setRarity(weaponDTO.getRarity());
		weaponDTO.setCost(weaponDTO.getCost());
		weaponDTO.setDamage(weaponDTO.getDamage());
		weaponDTO.setDamage_type(weaponDTO.getDamage_type());
		weaponDTO.setProperties(weaponDTO.getProperties());
		weaponDTO.setAdditional_abilities(weaponDTO.getAdditional_abilities());
		
		WeaponDTO updated = new WeaponDTO(1, characterDTO, weapon.getName(), weapon.getBase_weapon(), weapon.getWeapon_type(), weapon.getRarity(), 
				weapon.getCost(), weapon.getDamage(), weapon.getDamage_type(), weapon.getProperties(), weapon.getAdditional_abilities());
		
		when(weaponRepository.existsById(id)).thenReturn(true);
		when(weaponRepository.getById(id)).thenReturn(weapon);
		when(weaponRepository.save(weapon)).thenReturn(weapon);
		when(modelMapper.map(weapon, WeaponDTO.class)).thenReturn(updated);
		
		// Act
		WeaponDTO actual = weaponService.updateWeapon(weaponDTO, id);
		
		// Assert
		assertEquals(updated, actual);
		verify(weaponRepository).existsById(id);
		verify(weaponRepository).getById(id);
		verify(weaponRepository).save(weapon);
		verify(modelMapper).map(weapon, WeaponDTO.class);
	}
	
	@Test
	public void invalidUpdateTest() {
		// Arrange
		int id = 456;
		UpdateWeaponDTO weaponDTO = new UpdateWeaponDTO();
		when(weaponRepository.existsById(id)).thenReturn(false);
		
		// Act
		EntityNotFoundException exception = Assertions.assertThrows(EntityNotFoundException.class, () -> {
			weaponService.updateWeapon(weaponDTO, id);
		});
		
		// Assert
		String expectedMessage = "Weapon not found with id " + id;
		assertEquals(expectedMessage, exception.getMessage());
		verify(weaponRepository).existsById(id);
	}
}
