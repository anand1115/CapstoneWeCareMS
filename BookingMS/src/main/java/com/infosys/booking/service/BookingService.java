package com.infosys.booking.service;

import java.util.List;

import com.infosys.booking.dto.BookingDTO;

public interface BookingService{
	BookingDTO BookAppointment(BookingDTO bookDTO);
	BookingDTO ResheduleAppointment(long bookingId,BookingDTO bookingDTO);
	void CancelAppointment(long bookingId);
	List<BookingDTO> ShowAllAppointments(long userId);
}
