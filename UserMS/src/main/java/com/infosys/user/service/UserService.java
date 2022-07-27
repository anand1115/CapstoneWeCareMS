package com.infosys.user.service;

import java.util.List;

import com.infosys.user.dto.BookingDTO;
import com.infosys.user.dto.UserDTO;



public interface UserService {
	UserDTO CreateUser(UserDTO userDto);
	UserDTO GetProfile(long userId);
	List<BookingDTO> ShowAllAppointments(long userId);
}
