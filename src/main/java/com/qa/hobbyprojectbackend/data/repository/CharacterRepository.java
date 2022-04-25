package com.qa.hobbyprojectbackend.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.hobbyprojectbackend.data.entity.MyCharacter;

@Repository
public interface CharacterRepository extends JpaRepository<MyCharacter, Integer> {

}
