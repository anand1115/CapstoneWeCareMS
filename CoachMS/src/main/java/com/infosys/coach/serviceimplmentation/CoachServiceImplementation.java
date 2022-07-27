package com.infosys.coach.serviceimplmentation;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.infosys.coach.dto.CoachDTO;
import com.infosys.coach.entity.Coach;
import com.infosys.coach.exception.ResourceNotFoundException;
import com.infosys.coach.repository.CoachRepository;
import com.infosys.coach.service.CoachService;



@Service
public class CoachServiceImplementation implements CoachService {
	
	private CoachRepository coachRepo;
	private ModelMapper mapper;
	

	public CoachServiceImplementation(CoachRepository coachRepo, ModelMapper mapper) {
		this.coachRepo = coachRepo;
		this.mapper = mapper;
	}

	@Override
	public CoachDTO CreateCoach(CoachDTO coachDto) {
		return getDTO(coachRepo.save(getEntity(coachDto)));
	}

	@Override
	public CoachDTO GetProfile(long coachId) {
		Coach coach=coachRepo.findById(coachId).orElseThrow(()->new ResourceNotFoundException("Coach", "Id", ""+coachId));
		return getDTO(coach);
	}
	
	public Coach getEntity(CoachDTO coach) {
		return mapper.map(coach, Coach.class);
	}
	
	public CoachDTO getDTO(Coach coach) {
		return mapper.map(coach, CoachDTO.class);
	}
}
