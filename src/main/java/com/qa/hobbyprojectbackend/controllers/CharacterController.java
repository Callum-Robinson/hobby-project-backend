package com.qa.hobbyprojectbackend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.hobbyprojectbackend.dto.MyCharacterDTO;
import com.qa.hobbyprojectbackend.service.CharacterService;

@RestController
@RequestMapping(path = "/user")
@CrossOrigin("*")
public class CharacterController {

	private CharacterService characterService;
	
	@Autowired
	public CharacterController(CharacterService characterService) {
		this.characterService = characterService;
	}
	
	// get mapping for read all
	public ResponseEntity<List<MyCharacterDTO>> getCharacters() {
		return ResponseEntity.ok(characterService.getCharacters());
	}
	
	// get mapping for read by character id
	
}
