package com.qa.hobbyprojectbackend.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.hobbyprojectbackend.dto.MyCharacterDTO;
import com.qa.hobbyprojectbackend.dto.NewCharacterDTO;
import com.qa.hobbyprojectbackend.dto.UpdateCharacterDTO;
import com.qa.hobbyprojectbackend.service.CharacterService;

@RestController
@RequestMapping(path = "/character")
@CrossOrigin("*")
public class CharacterController {

	private CharacterService characterService;
	
	@Autowired
	public CharacterController(CharacterService characterService) {
		this.characterService = characterService;
	}
	
	// get mapping for read all
	@GetMapping
	public ResponseEntity<List<MyCharacterDTO>> getCharacters() {
		return ResponseEntity.ok(characterService.getCharacters());
	}
	
	// get mapping for read by character id
	@GetMapping(path = "/{id)")
	public ResponseEntity<MyCharacterDTO> getCharacter(@PathVariable(name = "id") int id) {
		MyCharacterDTO character = characterService.getCharacter(id);
		return new ResponseEntity<>(character, HttpStatus.OK);
	}
	
	// post mapping for create character
	@PostMapping
	public ResponseEntity<MyCharacterDTO> createCharacter(@Valid @RequestBody NewCharacterDTO character) {
		MyCharacterDTO newCharacter = characterService.createCharacter(character);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", "http://localhost:8080/character/" + newCharacter.getId());
		
		return new ResponseEntity<>(newCharacter, headers, HttpStatus.CREATED);
	}
	
	// put mapping for updating character
	@PutMapping(path = "/{id}")
	public ResponseEntity<MyCharacterDTO> updateCharacter(@RequestBody UpdateCharacterDTO updateCharacterDTO, 
			@PathVariable(name = "id") int id) {
		return ResponseEntity.ok(characterService.updateCharacter(updateCharacterDTO, id));
	}
	
	// delete mapping for deleting characters
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> deleteCharacter(@PathVariable(name = "id") int id) {
		MyCharacterDTO deletedCharacter = characterService.getCharacter(id);
		characterService.deleteCharacter(id);
		return ResponseEntity.ok(deletedCharacter);
	}
}
