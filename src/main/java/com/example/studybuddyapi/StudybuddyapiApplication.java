package com.example.studybuddyapi;

import java.util.ArrayList;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.studybuddyapi.model.Institution;
import com.example.studybuddyapi.model.Pair;
import com.example.studybuddyapi.model.Program;
import com.example.studybuddyapi.model.User;
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
			new Institution("AUAY", "University of the Fraser Valley", "33844 King Road", "Abbotsford", "British Columbia","Canada"),
			new Institution("EUAX", "University of Waterloo", "200 University Ave W", "Waterloo", "Ontario", "Canada"),
			new Institution("EUAV", "University of Toronto", "27 King's College Cir", "Toronto", "Ontario", "Canada"),
	};
	
	User[] users = {
			new User("testinguser1@test.com", "123456", "user 1", "testing", "111-111-1111", programs[0], institutions[0]), // 0
			new User("testinguser2@test.com", "123456", "user 2", "testing", "111-111-2222", programs[2], institutions[2]), // 1
			new User("testinguser3@test.com", "123456", "user 3", "testing", "111-111-3333", programs[0], institutions[0]), // 2
			new User("testinguser4@test.com", "123456", "user 4", "testing", "111-111-4444", programs[1], institutions[1]), // 3
	};
	
	private void loadTestData(ProgramRepository programRepo, IntitutionRepository institutionRepo, UserRepository userRepo, PairRepository pairRepo) {
		ArrayList<Program> programList = new ArrayList<>();
		ArrayList<Institution> institutionList = new ArrayList<>();
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
		userRepo.findAll().forEach(System.out::println);
		pairRepo.findAll().forEach(System.out::println);
	}

	@Bean
	ApplicationRunner initPrograms(ProgramRepository programRepo, IntitutionRepository institutionRepo, UserRepository userRepo, PairRepository pairRepo) {
		return args -> {			
			loadTestData(programRepo, institutionRepo, userRepo, pairRepo);
		};
	}
}
