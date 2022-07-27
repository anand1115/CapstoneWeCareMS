package com.infosys.coach.service;

import com.infosys.coach.dto.CoachDTO;

public interface CoachService {
	CoachDTO CreateCoach(CoachDTO coachDto);
	CoachDTO GetProfile(long coachId);
}

