package com.infosys.user;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.infosys.user.dto.BookingDTO;

@FeignClient(name = "BookingMS",url = "http://localhost:9000/booking/")
public interface BookingFiegnClient {
	
	@RequestMapping("{userID}")
	List<BookingDTO> getAllAppointments(@PathVariable(value = "userID") long userID); 

}
