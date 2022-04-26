package com.qa.hobbyprojectbackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
