package com.example.studybuddyapi.controller;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.studybuddyapi.model.ERole;
import com.example.studybuddyapi.model.Role;
import com.example.studybuddyapi.model.User;
import com.example.studybuddyapi.repositories.RoleRepository;
import com.example.studybuddyapi.repositories.UserRepository;

import jwt.JwtUtils;
import payload.JwtResponse;
import payload.MessageResponse;
import payload.SignInDto;
import payload.SignUpDto;
import services.UserDetailsImpl;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private RoleRepository roleRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	JwtUtils jwtUtils;
	
	@PostMapping("/login")
	public ResponseEntity<JwtResponse> authenticateUser(@RequestBody SignInDto signinDto) {
		try {
			Authentication auth = authManager.authenticate(
					new UsernamePasswordAuthenticationToken(signinDto.getEmail(), signinDto.getPassword())
			);
			
			SecurityContextHolder.getContext().setAuthentication(auth);
			String jwt = jwtUtils.generateJwtToken(auth);
			UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
			Set<String> roles = userDetails.getAuthorities().stream().map((role) -> role.getAuthority()).collect(Collectors.toSet());
					
			return new ResponseEntity<>(new JwtResponse(jwt, userDetails.getId(), userDetails.getEmail(), roles), HttpStatus.OK);
	
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/signup")
	public ResponseEntity<MessageResponse> registerUser(@RequestBody SignUpDto signupDto) {
		try {
			// checking for email exists in the database
			if (userRepo.existsByEmail(signupDto.getEmail())) {
				return new ResponseEntity<>(new MessageResponse("Email is already exists!"), HttpStatus.BAD_REQUEST);
			} else {
				Set<Role> roles = new HashSet<>();
				// By default set new user role to USER 
				Role userRole = roleRepo.findByName(ERole.USER).orElseThrow(() -> new RuntimeException("Error: ROLE is not found"));
				roles.add(userRole);
				
				User newUser = new User();
				newUser.setEmail(signupDto.getEmail());
				newUser.setPassword(passwordEncoder.encode(signupDto.getPassword()));
				newUser.setFirstName(signupDto.getFirstName());
				newUser.setLastName(signupDto.getLastName());
				newUser.setPhoneNumber(signupDto.getPhoneNum());
				newUser.setRoles(roles);
				
				userRepo.save(newUser);
				
				return new ResponseEntity<>(new MessageResponse("User is registered!"), HttpStatus.CREATED);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
