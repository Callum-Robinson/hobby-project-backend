package com.qa.hobbyprojectbackend.configuration;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.qa.hobbyprojectbackend.data.entity.MyCharacter;
import com.qa.hobbyprojectbackend.data.entity.Weapon;
import com.qa.hobbyprojectbackend.data.repository.CharacterRepository;
import com.qa.hobbyprojectbackend.data.repository.WeaponRepository;

@Profile("dev")
@Configuration
public class DevStartupListener implements ApplicationListener<ApplicationReadyEvent> {

	private CharacterRepository characterRepository;
	private WeaponRepository weaponRepository;
	
	@Autowired
	public DevStartupListener(CharacterRepository characterRepository, WeaponRepository weaponRepository) {
		this.characterRepository = characterRepository;
		this.weaponRepository = weaponRepository;
	}
	
	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		List<MyCharacter> characters = characterRepository.saveAll(List.of(
				new MyCharacter("Teoz", "Leonine", "", "Paladin", 5, "Oath of Vengeance", "Knight"),
				new MyCharacter("Maria", "Tabaxi", "", "Rogue", 5, "Swashbuckler", "Sailor")
				));
		
		MyCharacter character = characters.stream().filter(c -> c.getName().equals("Teoz")).findFirst().orElse(null);
		
		List<Weapon> weapons = weaponRepository.saveAll(List.of(
				new Weapon("Manticore's Wrath", "Greataxe", "Martial Melee Weapon", "Very Rare", "30gp", "1d12 + 2", 
						"Slashing", "Heavy, Two-Handed, Ranged", "+2 to attack rolls, ranged attack launching the spikes from the axe"),
				new Weapon("Blackened Steel Heavy Hammer", "Maul", "Martial Melee Weapon", "Rare", "10gp", "2d6 + 1",
						"Bludgeoning", "Heavy, Two-Handed", "+1 to hit and damage, has a ground attack")
				));
	}
}
