package com.qa.hobbyprojectbackend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.hobbyprojectbackend.data.entity.MyCharacter;
import com.qa.hobbyprojectbackend.data.repository.CharacterRepository;
import com.qa.hobbyprojectbackend.dto.MyCharacterDTO;
import com.qa.hobbyprojectbackend.dto.NewCharacterDTO;
import com.qa.hobbyprojectbackend.dto.UpdateCharacterDTO;
import com.qa.hobbyprojectbackend.dto.WeaponDTO;

@Service
public class CharacterService {

	private CharacterRepository characterRepository;
	private ModelMapper modelMapper;
	private WeaponService weaponService;
	
	@Autowired
	public CharacterService(CharacterRepository characterRepository, ModelMapper modelMapper) {
		super();
		this.characterRepository = characterRepository;
		this.modelMapper = modelMapper;
		this.weaponService = weaponService;
	}
	
	// Maps character to DTO
	private MyCharacterDTO toDTO(MyCharacter character) {
		return this.modelMapper.map(character, MyCharacterDTO.class);
	}
	
	// Get all Characters
	public List<MyCharacterDTO> getCharacters() {
		List<MyCharacter> characters = characterRepository.findAll();
		List<MyCharacterDTO> dtos = new ArrayList<>();
		
		for (MyCharacter character : characters) {
			dtos.add(this.toDTO(character));
		}
		
		return dtos;
	}
	
	// Get a Character by id
	public MyCharacterDTO getCharacter(int id) {
		Optional<MyCharacter> character = characterRepository.findById(id);
		
		if (character.isPresent()) {
			return this.toDTO(character.get());
		}
		throw new EntityNotFoundException("Character not found with id " + id);
	}
	
	// Create a Character
	public MyCharacterDTO createCharacter(NewCharacterDTO character) {
		MyCharacter toSave = this.modelMapper.map(character, MyCharacter.class);
		MyCharacter newCharacter = characterRepository.save(toSave);
		return this.toDTO(newCharacter);
	}
	
	// Update a Character
	@Transactional
	public MyCharacterDTO updateCharacter(UpdateCharacterDTO character, int id) {
		if (characterRepository.existsById(id)) {
			MyCharacter savedCharacter = characterRepository.getById(id);
			savedCharacter.setName(character.getName());
			savedCharacter.setRace(character.getRace());
			savedCharacter.setSubrace(character.getSubrace());
			savedCharacter.setCharacter_class(character.getCharacter_class());
			savedCharacter.setLevel(character.getLevel());
			savedCharacter.setArchetype(character.getArchetype());
			savedCharacter.setBackground(character.getBackground());
			
			return this.toDTO(savedCharacter);
		}
		throw new EntityNotFoundException("Character not found with id " + id);
	}
	
	// Delete a Character
	public void deleteCharacter(int id) {
		if (characterRepository.existsById(id)) {
			characterRepository.deleteById(id);
			return;
		}
		throw new EntityNotFoundException("Character not found with id " + id);
	}
	
	// Get characters weapons
	public List<WeaponDTO> getCharacterWeapons(int characterId) {
		MyCharacterDTO character = this.getCharacter(characterId);
		List<WeaponDTO> weapons = weaponService.getWeaponsByCharacterId(characterId);
		weapons.forEach(weapon -> weapon.setMyCharacterDTO(character));
		return weapons;
	}
}
