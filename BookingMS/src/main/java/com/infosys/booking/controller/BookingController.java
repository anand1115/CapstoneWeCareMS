package com.infosys.booking.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.booking.dto.BookingDTO;
import com.infosys.booking.serviceimplementation.BookingServiceimplmentation;



@RestController
@RequestMapping("/booking/")
public class BookingController {
	private BookingServiceimplmentation bookingService;

	public BookingController(BookingServiceimplmentation bookingService) {
		super();
		this.bookingService = bookingService;
	}
	
	@PostMapping
	public ResponseEntity<Object> createBooking(@Valid @RequestBody BookingDTO booking) {
		return new ResponseEntity<>(bookingService.BookAppointment(booking), HttpStatus.CREATED);
	}
	
	@PutMapping("/{bookingID}")
	public ResponseEntity<Object> updateBooking(@Valid @RequestBody BookingDTO booking,@PathVariable(value = "bookingID") long bookingID){
		return new ResponseEntity<>(bookingService.ResheduleAppointment(bookingID, booking),HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/{bookingID}")
	public ResponseEntity<Object> deleteBooking(@PathVariable(value = "bookingID") long bookingID){
		bookingService.CancelAppointment(bookingID);
		return new ResponseEntity<>("Booking Cancelled Successfully",HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/{userID}")
	public ResponseEntity<Object> getAllAppointments(@PathVariable(value = "userID") long userID){
		if(userID==1L) {
			throw new RuntimeException();
		}
		return new ResponseEntity<>(bookingService.ShowAllAppointments(userID),HttpStatus.ACCEPTED);
	}
	
	
}
