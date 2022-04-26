package com.qa.hobbyprojectbackend.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.hobbyprojectbackend.dto.NewWeaponDTO;
import com.qa.hobbyprojectbackend.dto.WeaponDTO;
import com.qa.hobbyprojectbackend.service.WeaponService;

@RestController
@RequestMapping(path = "/weapon")
@CrossOrigin("*")
public class WeaponController {

	private WeaponService weaponService;
	
	@Autowired
	public WeaponController(WeaponService weaponService) {
		this.weaponService = weaponService;
	}
	
	// Get all weapons mapping
	@GetMapping
	public ResponseEntity<List<WeaponDTO>> getWeapons() {
		return ResponseEntity.ok(weaponService.getWeapons());
	}
	
	// Get weapon by weapon id mapping
	@GetMapping(path = "/{id}")
	public ResponseEntity<WeaponDTO> getWeapon(@PathVariable(name = "id") int id) {
		return ResponseEntity.ok(weaponService.getWeapon(id));
	}
	
	// Post mapping for creating weapons
	@PostMapping
	public ResponseEntity<WeaponDTO> createWeapon(@Valid @RequestBody NewWeaponDTO weapon) {
		WeaponDTO newWeapon = weaponService.createWeapon(weapon);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", "http://localhost:8080/weapon/" + newWeapon.getId());
		
		return new ResponseEntity<>(newWeapon, headers, HttpStatus.CREATED);
	}
	
	
}
