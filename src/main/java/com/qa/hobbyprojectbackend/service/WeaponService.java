package com.qa.hobbyprojectbackend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.hobbyprojectbackend.data.entity.Weapon;
import com.qa.hobbyprojectbackend.data.repository.WeaponRepository;
import com.qa.hobbyprojectbackend.dto.NewWeaponDTO;
import com.qa.hobbyprojectbackend.dto.WeaponDTO;

@Service
public class WeaponService {

	private WeaponRepository weaponRepository;
	private ModelMapper modelMapper;
	
	@Autowired
	public WeaponService(WeaponRepository weaponRepository, ModelMapper modelMapper) {
		super();
		this.weaponRepository = weaponRepository;
		this.modelMapper = modelMapper;
	}
	
	// Maps weapon to DTO
	private WeaponDTO toDTO(Weapon weapon) {
		return modelMapper.map(weapon, WeaponDTO.class);
	}
	
	// Get all weapons
	public List<WeaponDTO> getWeapons() {
		List<Weapon> weapons = weaponRepository.findAll();
		List<WeaponDTO> dtos = new ArrayList<>();
		
		for (Weapon weapon : weapons) {
			dtos.add(this.toDTO(weapon));
		}
		return dtos;
	}
	
	// Get weapons by character id
	public List<WeaponDTO> getWeaponsByCharacterId(int id) {
		List<Weapon> weapons = weaponRepository.findByMyCharacterId(id);
		List<WeaponDTO> dtos = new ArrayList<>();
		
		for (Weapon weapon : weapons) {
			dtos.add(this.toDTO(weapon));
		}
		return dtos;
	}
	
	// Get weapon by weapon id
	public WeaponDTO getWeapon(int id) {
		Optional<Weapon> weapon = weaponRepository.findById(id);
		
		if (weapon.isPresent()) {
			return this.toDTO(weapon.get());
		}
		throw new EntityNotFoundException("Weapon not found with id " + id);
	}
	
	// Create weapon
	public WeaponDTO createWeapon(NewWeaponDTO weapon) {
		Weapon toSave = this.modelMapper.map(weapon, Weapon.class);
		Weapon newWeapon = weaponRepository.save(toSave);
		return this.toDTO(newWeapon);
	}
	
	
}
