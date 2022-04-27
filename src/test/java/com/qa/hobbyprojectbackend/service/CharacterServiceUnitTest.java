package com.qa.hobbyprojectbackend.service;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.qa.hobbyprojectbackend.data.entity.MyCharacter;
import com.qa.hobbyprojectbackend.data.repository.CharacterRepository;
import com.qa.hobbyprojectbackend.dto.MyCharacterDTO;

@ExtendWith(MockitoExtension.class)
public class CharacterServiceUnitTest {

	@Mock
	private CharacterRepository characterRepository;
	
	@Mock
	private ModelMapper modelMapper;
	
	@InjectMocks
	private CharacterService characterService;
	
	private List<MyCharacter> characters;
	private List<MyCharacterDTO> characterDTOs;
	
	@BeforeEach
	public void init() {
		characters = List.of(
				new MyCharacter(1, "Teoz", "Leonine", "", "Paladin", 5, "Oath of Vengeance", "Knight"),
				new MyCharacter(2, "Maria", "Tabaxi", "", "Rogue", 5, "Swashbuckler", "Sailor"));
		characterDTOs = List.of(
				new MyCharacterDTO(1, "Teoz", "Leonine", "", "Paladin", 5, "Oath of Vengeance", "Knight"),
				new MyCharacterDTO(2, "Maria", "Tabaxi", "", "Rogue", 5, "Swashbuckler", "Sailor"));
	}
	
	
}
