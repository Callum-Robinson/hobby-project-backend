package com.qa.hobbyprojectbackend.configuration;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.qa.hobbyprojectbackend.data.entity.MyCharacter;
import com.qa.hobbyprojectbackend.data.repository.CharacterRepository;

@Profile("dev")
@Configuration
public class DevStartupListener implements ApplicationListener<ApplicationReadyEvent> {

	private CharacterRepository characterRepository;
	
	@Autowired
	public DevStartupListener(CharacterRepository characterRepository) {
		this.characterRepository = characterRepository;
	}
	
	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		List<MyCharacter> characters = characterRepository.saveAll(List.of(
				new MyCharacter("Teoz", "Leonine", "", "Paladin", 5, "Oath of Vengeance", "Knight"),
				new MyCharacter("Maria", "Tabaxi", "", "Rogue", 5, "Swashbuckler", "Sailor")
				));
	}
}
