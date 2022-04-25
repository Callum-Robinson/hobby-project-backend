package com.qa.hobbyprojectbackend.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.hobbyprojectbackend.data.repository.CharacterRepository;

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
}
