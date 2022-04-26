package com.qa.hobbyprojectbackend.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qa.hobbyprojectbackend.data.entity.Weapon;

public interface WeaponRepository extends JpaRepository<Weapon, Integer> {

	List<Weapon> findByMyCharacterId(int id);
}
