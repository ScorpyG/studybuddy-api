package com.example.studybuddyapi;

import java.util.ArrayList;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.studybuddyapi.model.Hobby;
import com.example.studybuddyapi.model.Institution;
import com.example.studybuddyapi.model.Pair;
import com.example.studybuddyapi.model.Program;
import com.example.studybuddyapi.model.User;
import com.example.studybuddyapi.repositories.HobbyRepository;
import com.example.studybuddyapi.repositories.IntitutionRepository;
import com.example.studybuddyapi.repositories.PairRepository;
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
			new Program("BIO","Biology"),
			new Program("PHYS","Physics"),
			new Program("GEOG","Geography"),
			new Program("MATH","Mathematics"),
			new Program("ENGL","English"),
			new Program("PHIL","Philosophy"),
			new Program("HIST","History"),
			new Program("PSYC","Psychology"),
			new Program("LAWS","Laws"),
			new Program("CRIM","Criminology")
	};
	
	Institution[] institutions = {
			new Institution("AJAE", "Douglas College", "700 Royal Ave", "New Westminster", "British Columbia","Canada"),
			new Institution("AUAA", "University of British Columbia", "1874 East Mall", "Vancouver", "British Columbia","Canada"),
			new Institution("AUAF", "University of Victoria", "3800 Finnerty Rd","Victoria", "British Columbia","Canada"),
			new Institution("EUAX", "University of Waterloo", "200 University Ave W", "Waterloo", "Ontario", "Canada"),
			new Institution("EUAV", "University of Toronto", "27 King's College Cir", "Toronto", "Ontario", "Canada"),
	};
	
	Hobby[] hobbies = {
			new Hobby("Basketball","Active Hobbies"),
			new Hobby("Golf","Active Hobbies"),
			new Hobby("Running","Active Hobbies"),
			new Hobby("Volleyball","Active Hobbies"),
			new Hobby("Yoga","Active Hobbies"),
			new Hobby("Soccer","Active Hobbies"),
			new Hobby("Cheerleading","Active Hobbies"),
			new Hobby("Swimming","Active Hobbies"),
			new Hobby("Football","Active Hobbies"),
			new Hobby("Other","Active Hobbies"),
			new Hobby("Painting","Creative Hobbies"),
			new Hobby("Poetry writing","Creative Hobbies"),
			new Hobby("Jewelry making","Creative Hobbies"),
			new Hobby("Clay crafts","Creative Hobbies"),
			new Hobby("Magic","Creative Hobbies"),
			new Hobby("Pottery/Ceramics","Creative Hobbies"),
			new Hobby("Makeup art","Creative Hobbies"),
			new Hobby("Hair styling","Creative Hobbies"),
			new Hobby("Photography","Creative Hobbies"),
			new Hobby("Sewing","Creative Hobbies"),
			new Hobby("Knitting","Creative Hobbies"),
			new Hobby("Embroidery","Creative Hobbies"),
			new Hobby("Crochet","Creative Hobbies"),
			new Hobby("Acting","Creative Hobbies"),
			new Hobby("Gardening","Creative Hobbies"),
			new Hobby("Design","Creative Hobbies"),
			new Hobby("Other","Creative Hobbies"),
			new Hobby("Singing","Musical Hobbies"),
			new Hobby("Dancing","Musical Hobbies"),
			new Hobby("Listening to music","Musical Hobbies"),
			new Hobby("Playing instruments","Musical Hobbies"),
			new Hobby("Other","Musical Hobbies"),
			new Hobby("Reading","Mental Hobbies"),
			new Hobby("Journaling","Mental Hobbies"),
			new Hobby("Writing","Mental Hobbies"),
			new Hobby("Crossword puzzles","Mental Hobbies"),
			new Hobby("Sudoku","Mental Hobbies"),
			new Hobby("Word scrambles","Mental Hobbies"),
			new Hobby("Other","Mental Hobbies"),
			new Hobby("Pilates","Active Hobbies"),
			new Hobby("Ice skating","Active Hobbies"),
			new Hobby("Stretching","Active Hobbies"),
			new Hobby("Hiking","Active Hobbies"),
			new Hobby("Fishing","Active Hobbies"),
			new Hobby("Skiing","Active Hobbies"),
			new Hobby("Weight lifting","Active Hobbies"),
			new Hobby("Kickboxing","Active Hobbies"),
			new Hobby("Boxing","Active Hobbies"),
			new Hobby("Board games","Active Hobbies")
	};
	
	// TODO: update User class to incorporate hobby
	User[] users = {
			new User("testinguser1@test.com", "123456", "user 1", "testing", "111-111-1111", programs[0], institutions[0]), // 0
			new User("testinguser2@test.com", "123456", "user 2", "testing", "111-111-2222", programs[2], institutions[2]), // 1
			new User("testinguser3@test.com", "123456", "user 3", "testing", "111-111-3333", programs[0], institutions[3]), // 2
			new User("testinguser4@test.com", "123456", "user 4", "testing", "111-111-4444", programs[1], institutions[1]), // 3
	};
	
	private void loadTestData(ProgramRepository programRepo, IntitutionRepository institutionRepo, HobbyRepository hobbyRepo, UserRepository userRepo, PairRepository pairRepo) {
		ArrayList<Program> programList = new ArrayList<>();
		ArrayList<Institution> institutionList = new ArrayList<>();
		ArrayList<Hobby> hobbyList = new ArrayList<>();
		ArrayList<User> userList = new ArrayList<>();
		ArrayList<Pair> pairList = new ArrayList<>();
		
		// Create programs
		for (int i = 0; i < programs.length; i++) {
			programList.add(programs[i]);
		}
		programRepo.saveAll(programList);
		
		// Create institutions
		for (int i = 0; i < institutions.length; i++) {
			institutionList.add(institutions[i]);
		}
		institutionRepo.saveAll(institutionList);
    
	    // Create hobbies
	    for (int i = 0; i < hobbies.length; i++) {
	      hobbyList.add(hobbies[i]);
	    }
	    hobbyRepo.saveAll(hobbyList);
		
		// Create users
		for (int i = 0; i < users.length; i++) {
			userList.add(users[i]);
		}
		userRepo.saveAll(userList);
		
		// Create pairs
		for (int i = 0; i < users.length; i++) {
			for (int j = 0; j < users.length; j++) {
				if (i != j) {
					double randomMqp = Math.round(((Math.random() * 5.4) * 100.0 ) / 100.0);
					Pair newPair = new Pair(users[i], users[j], randomMqp, false, false);
					
					pairList.add(newPair);
				}
			}
		}
		pairRepo.saveAll(pairList);
  
		System.out.print("\nServer is running on localhost:8080\n");
		programRepo.findAll().forEach(System.out::println);
		institutionRepo.findAll().forEach(System.out::println);
		hobbyRepo.findAll().forEach(System.out::println);
		userRepo.findAll().forEach(System.out::println);
		pairRepo.findAll().forEach(System.out::println);
	}

	@Bean
	ApplicationRunner initPrograms(ProgramRepository programRepo, IntitutionRepository institutionRepo, HobbyRepository hobbyRepo, UserRepository userRepo, PairRepository pairRepo) {
		return args -> {			
			loadTestData(programRepo, institutionRepo, hobbyRepo, userRepo, pairRepo);
		};
	}
}
