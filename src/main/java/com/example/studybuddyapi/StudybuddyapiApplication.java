package com.example.studybuddyapi;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.studybuddyapi.model.Hobby;
import com.example.studybuddyapi.model.Institution;
import com.example.studybuddyapi.model.Program;
import com.example.studybuddyapi.model.User;
import com.example.studybuddyapi.repositories.HobbyRepository;
import com.example.studybuddyapi.repositories.IntitutionRepository;
import com.example.studybuddyapi.repositories.ProgramRepository;
import com.example.studybuddyapi.repositories.UserRepository;

@SpringBootApplication
public class StudybuddyapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudybuddyapiApplication.class, args);
	}
	
	Program[] programs = {
			new Program("CSCI","Computer Science"),
			new Program("PSCI","Political Science"),
			new Program("BUS","Business"),
			new Program("ACCT","Accounting"),
			new Program("ECON","Economics"),
			new Program("ENGR","Engineering"),
			new Program("NURS","Nursing"),
			new Program("CHEM","Chemistry"),
	};
	
	Institution[] institutes = {
			new Institution("AJAE", "Douglas College", "700 Royal Ave", "New Westminster", "British Columbia","Canada"),
			new Institution("AUAA", "University of British Columbia", "1874 East Mall", "Vancouver", "British Columbia","Canada"),	
			new Institution("AUAE", "Simon Fraser University", "8888 University Drive", "Burnaby", "British Columbia","Canada"),
			new Institution("AVAD", "Vancouver Community College", "250 West Pender St.", "Vancouver", "British Columbia","Canada"),
			new Institution("AUAF", "University of Victoria", "3800 Finnerty Rd","Victoria", "British Columbia","Canada"),	
			new Institution("AUAY", "University of the Fraser Valley", "33844 King Road", "Abbotsford", "British Columbia","Canada"),		
	};
	
	@Bean
	ApplicationRunner initPrograms(ProgramRepository programRepo) {
		return args -> {
			programRepo.save(new Program("CSCI","Computer Science"));
			programRepo.save(new Program("PSCI","Political Science"));
			programRepo.save(new Program("BUS","Business"));
			programRepo.save(new Program("ACCT","Accounting"));
			programRepo.save(new Program("ECON","Economics"));
			programRepo.save(new Program("ENGR","Engineering"));
			programRepo.save(new Program("NURS","Nursing"));
			programRepo.save(new Program("CHEM","Chemistry"));
			programRepo.save(new Program("BIO","Biology"));
			programRepo.save(new Program("PHYS","Physics"));
			programRepo.save(new Program("GEOG","Geography"));
			programRepo.save(new Program("MATH","Mathematics"));
			programRepo.save(new Program("ENGL","English"));
			programRepo.save(new Program("PHIL","Philosophy"));
			programRepo.save(new Program("HIST","History"));
			programRepo.save(new Program("PSYC","Psychology"));
			programRepo.save(new Program("LAWS","Laws"));
			programRepo.save(new Program("CRIM","Criminology"));
			programRepo.save(new Program("FINC","Finance"));
			programRepo.save(new Program("EDUC","Education"));
			programRepo.save(new Program("MUSC","Musics"));
			programRepo.save(new Program("PEFA","Performing & Fine Art"));
			programRepo.save(new Program("SCMG","Supply Chain Management"));
			
			programRepo.findAll().forEach(System.out::println);
		};
	}
	
	@Bean
	ApplicationRunner initInstitutions(IntitutionRepository institutionRepo) {
		return args -> {
			institutionRepo.save(
					new Institution("AJAE", "Douglas College", "700 Royal Ave",
							"New Westminster", "British Columbia","Canada")
			);
			institutionRepo.save(
					new Institution("AUAA", "University of British Columbia", "1874 East Mall",
							"Vancouver", "British Columbia","Canada")
			);
			institutionRepo.save(
					new Institution("AUAE", "Simon Fraser University", "8888 University Drive",
							"Burnaby", "British Columbia","Canada")
			);
			institutionRepo.save(
					new Institution("AVAD", "Vancouver Community College", "250 West Pender St.",
							"Vancouver", "British Columbia","Canada")
			);
			institutionRepo.save(
					new Institution("AUAF", "University of Victoria", "3800 Finnerty Rd",
							"Victoria", "British Columbia","Canada")
			);
			institutionRepo.save(
					new Institution("AUAY", "University of the Fraser Valley", "33844 King Road",
							"Abbotsford", "British Columbia","Canada")
			);
			
			institutionRepo.findAll().forEach(System.out::println);
		};
	}
	
	
	@Bean
	ApplicationRunner initHobbies (HobbyRepository hobbyRepo) {
		return args -> {
			hobbyRepo.save(new Hobby("Basketball","Active Hobbies"));
			hobbyRepo.save(new Hobby("Golf","Active Hobbies"));
			hobbyRepo.save(new Hobby("Running","Active Hobbies"));
			hobbyRepo.save(new Hobby("Soccer","Active Hobbies"));
			hobbyRepo.save(new Hobby("Volleyball","Active Hobbies"));
			hobbyRepo.save(new Hobby("Yoga","Active Hobbies"));
			hobbyRepo.save(new Hobby("Pilates","Active Hobbies"));
			hobbyRepo.save(new Hobby("Swimming","Active Hobbies"));
			hobbyRepo.save(new Hobby("Ice skating","Active Hobbies"));
			hobbyRepo.save(new Hobby("Football","Active Hobbies"));
			hobbyRepo.save(new Hobby("Stretching","Active Hobbies"));
			hobbyRepo.save(new Hobby("Hiking","Active Hobbies"));
			hobbyRepo.save(new Hobby("Cheerleading","Active Hobbies"));
			hobbyRepo.save(new Hobby("Fishing","Active Hobbies"));
			hobbyRepo.save(new Hobby("Skiing","Active Hobbies"));
			hobbyRepo.save(new Hobby("Weight lifting","Active Hobbies"));
			hobbyRepo.save(new Hobby("Kickboxing","Active Hobbies"));
			hobbyRepo.save(new Hobby("Boxing","Active Hobbies"));
			hobbyRepo.save(new Hobby("Board games","Active Hobbies"));
			hobbyRepo.save(new Hobby("Other","Active Hobbies"));

			hobbyRepo.save(new Hobby("Painting","Creative Hobbies"));
			hobbyRepo.save(new Hobby("Poetry writing","Creative Hobbies"));
			hobbyRepo.save(new Hobby("Jewelry making","Creative Hobbies"));
			hobbyRepo.save(new Hobby("Clay crafts","Creative Hobbies"));
			hobbyRepo.save(new Hobby("Magic","Creative Hobbies"));
			hobbyRepo.save(new Hobby("Pottery/Ceramics","Creative Hobbies"));
			hobbyRepo.save(new Hobby("Makeup art","Creative Hobbies"));
			hobbyRepo.save(new Hobby("Hair styling","Creative Hobbies"));
			hobbyRepo.save(new Hobby("Photography","Creative Hobbies"));
			hobbyRepo.save(new Hobby("Sewing","Creative Hobbies"));
			hobbyRepo.save(new Hobby("Knitting","Creative Hobbies"));
			hobbyRepo.save(new Hobby("Embroidery","Creative Hobbies"));
			hobbyRepo.save(new Hobby("Crochet","Creative Hobbies"));
			hobbyRepo.save(new Hobby("Acting","Creative Hobbies"));
			hobbyRepo.save(new Hobby("Gardening","Creative Hobbies"));
			hobbyRepo.save(new Hobby("Design","Creative Hobbies"));
			hobbyRepo.save(new Hobby("Other","Creative Hobbies"));
			
			hobbyRepo.save(new Hobby("Reading","Mental Hobbies"));
			hobbyRepo.save(new Hobby("Journaling","Mental Hobbies"));
			hobbyRepo.save(new Hobby("Writing","Mental Hobbies"));
			hobbyRepo.save(new Hobby("Crossword puzzles","Mental Hobbies"));
			hobbyRepo.save(new Hobby("Sudoku","Mental Hobbies"));
			hobbyRepo.save(new Hobby("Word scrambles","Mental Hobbies"));
			hobbyRepo.save(new Hobby("Languages","Mental Hobbies"));
			hobbyRepo.save(new Hobby("Games","Mental Hobbies"));
			hobbyRepo.save(new Hobby("Podcasts","Mental Hobbies"));
			hobbyRepo.save(new Hobby("Other","Mental Hobbies"));
			

			hobbyRepo.save(new Hobby("Singing","Musical Hobbies"));
			hobbyRepo.save(new Hobby("Dancing","Musical Hobbies"));
			hobbyRepo.save(new Hobby("Listening to music","Musical Hobbies"));
			hobbyRepo.save(new Hobby("Playing instruments","Musical Hobbies"));
			hobbyRepo.save(new Hobby("Other","Musical Hobbies"));
			
			
		};
	}
	
	@Bean
	ApplicationRunner initUsers(UserRepository userRepo) {
		return args -> {
			for (int i = 0; i <= 5; i++) {
				userRepo.save(new User(
						"test@" + i + ".com",
						"testing" + i,
						"name fn" + i,
						"name ln" + i,
						"testing" + i,
						programs[i],
						institutes[i])
				);
			}
		};
	}
}
