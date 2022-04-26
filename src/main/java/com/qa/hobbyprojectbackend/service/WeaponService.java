package com.qa.hobbyprojectbackend.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.qa.hobbyprojectbackend.data.repository.WeaponRepository;

@Service
public class WeaponService {

	private WeaponRepository weaponRepository;
	private ModelMapper modelMapper;
}
