package com.example.studybuddyapi;

import java.util.ArrayList;
import java.util.Random;

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
	
	// duplicate the method
	public double calculateMatchQualityScore(User mainUser, User interestUser) {
		double score = 0;
		
		String mainUserPrg = mainUser.getProgram().getCode();
		String interestUserPrg = interestUser.getProgram().getCode();
		
		String mainUserInstitution = mainUser.getInstitution().getInstitutionCode();
		String interestUserInstitution = interestUser.getInstitution().getInstitutionCode();
		String[] userHobbies = mainUser.getHobbies();
		String[] interestUserHobbies = interestUser.getHobbies();		
		
		// check for program of both users match add 3 points to the score
		if (mainUserPrg.equals(interestUserPrg)) {
			score += 3;
		}
		
		// check for institution of both users match add 2 points to the score
		if (mainUserInstitution.equals(interestUserInstitution)) {
			score += 2;
		}
		
		for (int i = 0; i < userHobbies.length; i++) {
			for (int j = 0; j < interestUserHobbies.length; j++) {
				if (userHobbies[i].replaceAll("\\s", "").equalsIgnoreCase(interestUserHobbies[j])) {
					score += 0.1;
				}
			}
		}
		
		return score;
	}
	
	private void loadTestData(ProgramRepository programRepo, IntitutionRepository institutionRepo, UserRepository userRepo, PairRepository pairRepo) {
		// Create programs records
		ArrayList<Program> programList = new ArrayList<>();
		programList.add(new Program("CSCI","Computer Science")); 	// 0
		programList.add(new Program("PSCI","Political Science")); 	// 1
		programList.add(new Program("BUS","Business")); 			// 2
		programList.add(new Program("ACCT","Accounting")); 			// 3
		programList.add(new Program("ECON","Economics")); 			// 4
		programList.add(new Program("ENGR","Engineering")); 		// 5
		programList.add(new Program("NURS","Nursing"));	 			// 6
		programList.add(new Program("CHEM","Chemistry")); 			// 7
		programList.add(new Program("BIO","Biology")); 				// 8
		programList.add(new Program("PHYS","Physics")); 			// 9
		programList.add(new Program("GEOG","Geography")); 			// 10
		programList.add(new Program("MATH","Mathematics")); 		// 11
		programList.add(new Program("ENGL","English")); 			// 12
		programList.add(new Program("PHIL","Philosophy")); 			// 13
		programList.add(new Program("HIST","History")); 			// 14
		programList.add(new Program("PSYC","Psychology")); 			// 15
		programList.add(new Program("LAWS","Laws")); 				// 16
		programList.add(new Program("CRIM","Criminology")); 		// 17
		programRepo.saveAll(programList);
		
		// Create institutions records
		ArrayList<Institution> institutionList = new ArrayList<>();
		institutionList.add(new Institution("AJAE", "Douglas College", "700 Royal Ave", "New Westminster", "British Columbia","Canada")); 			// 0
		institutionList.add(new Institution("AUAA", "University of British Columbia", "1874 East Mall", "Vancouver", "British Columbia","Canada")); // 1
		institutionList.add(new Institution("AUAF", "University of Victoria", "3800 Finnerty Rd","Victoria", "British Columbia","Canada")); 		// 2
		institutionList.add(new Institution("EUAX", "University of Waterloo", "200 University Ave W", "Waterloo", "Ontario", "Canada")); 			// 3
		institutionList.add(new Institution("EUAV", "University of Toronto", "27 King's College Cir", "Toronto", "Ontario", "Canada")); 			// 4
		institutionRepo.saveAll(institutionList);
		
	    String[] user1Hobbies = new String[] {"Chess", "Sudoku", "Journaling"};
	    String[] user2Hobbies = new String[] {"Singing", "Swimming", "Chess"};
	    String[] user3Hobbies = new String[] {"Basketball", "Knitting", "Poetry Writing"};
	    String[] user4Hobbies = new String[] {"Reading", "Journaling", "Gardening"};
	    
		// Create users record
		ArrayList<User> userList = new ArrayList<>();
		userList.add(new User("testinguser1@test.com", "123456", "user 1", "testing", "111-111-1111", programList.get(0), institutionList.get(0), user1Hobbies));
		userList.add(new User("testinguser2@test.com", "123456", "user 2", "testing", "111-111-2222", programList.get(1), institutionList.get(0), user2Hobbies));
		userList.add(new User("testinguser3@test.com", "123456", "user 3", "testing", "111-111-3333", programList.get(2), institutionList.get(1), user3Hobbies));
		userList.add(new User("testinguser4@test.com", "123456", "user 4", "testing", "111-111-4444", programList.get(3), institutionList.get(4), user4Hobbies));
		userRepo.saveAll(userList);
		
		// Create pairs
		ArrayList<Pair> pairList = new ArrayList<>();
		for (int i = 0; i < userList.size(); i++) {
			for (int j = 0; j < userList.size(); j++) {
				if (i != j) {
					double calculatedMqp = calculateMatchQualityScore(userList.get(i), userList.get(j));
					Random rd = new Random();
					Pair newPair = new Pair(userList.get(i), userList.get(j), calculatedMqp, rd.nextBoolean(), false);
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
			// populate the API with data
			loadTestData(programRepo, institutionRepo, userRepo, pairRepo);
		};
	}
}
