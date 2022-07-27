package com.infosys.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infosys.user.entity.UserEntity;



public interface UserRepository extends JpaRepository<UserEntity,Long> {
	UserEntity findBymobileNumber(long mobileNumber);
}

