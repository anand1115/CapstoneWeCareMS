package com.infosys.user.serviceimplementation;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;

import com.infosys.user.BookingFiegnClient;
import com.infosys.user.dto.BookingDTO;
import com.infosys.user.dto.UserDTO;
import com.infosys.user.entity.UserEntity;
import com.infosys.user.exception.ResourceNotFoundException;
import com.infosys.user.repository.UserRepository;
import com.infosys.user.service.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class UserServiceImplementation implements UserService {
	
	private UserRepository userRepo;
	private ModelMapper mapper;
	
	@Autowired
	BookingFiegnClient BOOKING;
	

	public UserServiceImplementation(UserRepository userRepo, ModelMapper mapper) {
		super();
		this.userRepo = userRepo;
		this.mapper = mapper;
	}

	@Override
	public UserDTO CreateUser(UserDTO userDto) {
		UserEntity user=this.getEntity(userDto);
		UserEntity newUser=userRepo.save(user);
		return this.getDTO(newUser);
	}

	@Override
	public UserDTO GetProfile(long userId) {
		UserEntity user=userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "Id", ""+userId));
		UserDTO userDto=this.getDTO(user);
		return userDto;
	}

	@Override
	@CircuitBreaker(name = "UserService",fallbackMethod = "fallbackShowAll")
	public List<BookingDTO> ShowAllAppointments(long userId) {
		userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "Id", ""+userId));
		List<BookingDTO> bookings=BOOKING.getAllAppointments(userId);
		return bookings;
	}
	
	public List<BookingDTO> fallbackShowAll(long userId,Throwable throwable){
		return new ArrayList<>();
	}
	
	public UserEntity getEntity(UserDTO userDto) {
		return mapper.map(userDto, UserEntity.class);
	}
	
	public UserDTO getDTO(UserEntity user) {
		return mapper.map(user, UserDTO.class);
	}

}
