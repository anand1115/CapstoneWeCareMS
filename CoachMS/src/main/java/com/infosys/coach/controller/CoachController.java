

package com.infosys.coach.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.coach.dto.CoachDTO;
import com.infosys.coach.serviceimplmentation.CoachServiceImplementation;



@RestController
@RequestMapping("/coach/")
public class CoachController {
	private CoachServiceImplementation coachService;

	public CoachController(CoachServiceImplementation coachService) {
		super();
		this.coachService = coachService;
	}
	
	@PostMapping
	public ResponseEntity<Object> createCoach(@Valid @RequestBody CoachDTO coach) {
		return new ResponseEntity<>(coachService.CreateCoach(coach), HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/{coachId}")
	public ResponseEntity<Object> createUser(@PathVariable(value = "coachId") long coachId) {
		return new ResponseEntity<>(coachService.GetProfile(coachId), HttpStatus.ACCEPTED);
	}
	

}
