package com.qa.hobbyprojectbackend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.hobbyprojectbackend.data.entity.MyCharacter;
import com.qa.hobbyprojectbackend.data.repository.CharacterRepository;
import com.qa.hobbyprojectbackend.dto.MyCharacterDTO;

@Service
public class CharacterService {

	private CharacterRepository characterRepository;
	private ModelMapper modelMapper;
	
	@Autowired
	public CharacterService(CharacterRepository characterRepository, ModelMapper modelMapper) {
		super();
		this.characterRepository = characterRepository;
		this.modelMapper = modelMapper;
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
}
