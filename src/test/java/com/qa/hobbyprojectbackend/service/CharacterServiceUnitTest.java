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
import com.qa.hobbyprojectbackend.data.repository.CharacterRepository;
import com.qa.hobbyprojectbackend.dto.MyCharacterDTO;
import com.qa.hobbyprojectbackend.dto.NewCharacterDTO;

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
	
	@Test
	public void getAllTest() {
		// Arrange
		when(characterRepository.findAll()).thenReturn(characters);
		when(modelMapper.map(characters.get(0), MyCharacterDTO.class)).thenReturn(characterDTOs.get(0));
		when(modelMapper.map(characters.get(1), MyCharacterDTO.class)).thenReturn(characterDTOs.get(1));
		
		// Act
		List<MyCharacterDTO> actual = characterService.getCharacters();
		
		// Assert
		assertEquals(characterDTOs, actual);
		verify(characterRepository).findAll();
		verify(modelMapper).map(characters.get(0), MyCharacterDTO.class);
		verify(modelMapper).map(characters.get(1), MyCharacterDTO.class);
	}
	
	@Test
	public void getByIdTest() {
		// Arrange
		MyCharacter character = characters.get(0);
		MyCharacterDTO characterDTO = characterDTOs.get(0);
		int id = character.getId();
		
		when(characterRepository.findById(id)).thenReturn(Optional.of(character));
		when(modelMapper.map(character, MyCharacterDTO.class)).thenReturn(characterDTO);
		
		// Act
		MyCharacterDTO actual = characterService.getCharacter(id);
		
		// Assert
		assertEquals(characterDTO, actual);
		verify(characterRepository).findById(id);
		verify(modelMapper).map(character, MyCharacterDTO.class);
	}
	
	@Test
	public void getByInvalidIdTest() {
		// Arrange
		int id = 300;
		when(characterRepository.findById(id)).thenReturn(Optional.empty());
		
		// Act
		EntityNotFoundException exception = Assertions.assertThrows(EntityNotFoundException.class, () -> {
			characterService.getCharacter(id);
		});
		
		// Assert
		String expectedMessage = "Character not found with id " + id;
		assertEquals(expectedMessage, exception.getMessage());
		verify(characterRepository).findById(id);
	}
	
	@Test
	public void createTest() {
		// Arrange
		MyCharacter character = characters.get(0);
		
		NewCharacterDTO characterDTO = new NewCharacterDTO();
		characterDTO.setName(character.getName());
		characterDTO.setRace(character.getRace());
		characterDTO.setSubrace(character.getSubrace());
		characterDTO.setCharacter_class(character.getCharacter_class());
		characterDTO.setLevel(character.getLevel());
		characterDTO.setArchetype(character.getArchetype());
		characterDTO.setBackground(character.getBackground());
		
		MyCharacterDTO newCharacter = new MyCharacterDTO(character.getId(), character.getName(), character.getRace(), 
				character.getSubrace(), character.getCharacter_class(), character.getLevel(), character.getArchetype(), character.getBackground());
		
		when(modelMapper.map(characterDTO, MyCharacter.class)).thenReturn(character);
		when(characterRepository.save(character)).thenReturn(character);
		when(modelMapper.map(character, MyCharacterDTO.class)).thenReturn(newCharacter);
		
		// Act
		MyCharacterDTO actual = characterService.createCharacter(characterDTO);
		
		// Assert
		assertEquals(newCharacter, actual);
		verify(modelMapper).map(characterDTO, MyCharacter.class);
		verify(characterRepository).save(character);
		verify(modelMapper).map(character, MyCharacterDTO.class);
	}
}
