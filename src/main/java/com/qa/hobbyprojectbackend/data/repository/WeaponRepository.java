package com.qa.hobbyprojectbackend.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.hobbyprojectbackend.data.entity.Weapon;

@Repository
public interface WeaponRepository extends JpaRepository<Weapon, Integer> {

}
