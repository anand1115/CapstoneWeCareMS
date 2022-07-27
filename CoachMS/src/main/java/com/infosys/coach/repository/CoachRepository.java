package com.infosys.coach.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.infosys.coach.entity.Coach;


public interface CoachRepository extends JpaRepository<Coach, Long> {
	
}

