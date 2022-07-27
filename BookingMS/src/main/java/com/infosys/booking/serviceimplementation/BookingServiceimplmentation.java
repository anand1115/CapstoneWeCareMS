package com.infosys.booking.serviceimplementation;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.infosys.booking.dto.BookingDTO;
import com.infosys.booking.dto.CoachDTO;
import com.infosys.booking.dto.UserDTO;
import com.infosys.booking.entity.Booking;
import com.infosys.booking.exception.ResourceNotFoundException;
import com.infosys.booking.exception.WeCareException;
import com.infosys.booking.repository.BookingRepository;
import com.infosys.booking.service.BookingService;

@Service
public class BookingServiceimplmentation implements BookingService {
	private BookingRepository bookingRepo;
	private ModelMapper mapper;

	@Autowired
	RestTemplate restTemplate;

	public BookingServiceimplmentation(BookingRepository bookingRepo, ModelMapper mapper) {
		super();
		this.bookingRepo = bookingRepo;
		this.mapper = mapper;
	}

	@Override
	public BookingDTO BookAppointment(BookingDTO bookDTO) {
		// TODO Auto-generated method stub
		long userID, coachID;
		try {
			userID = Long.parseLong(bookDTO.getUserId());
		} catch (Exception e) {
			throw new WeCareException(HttpStatus.BAD_REQUEST, "Invalid User ID");
		}
		try {
			coachID = Long.parseLong(bookDTO.getCoachId());
		} catch (Exception e) {
			throw new WeCareException(HttpStatus.BAD_REQUEST, "Invalid User ID");
		}

		ResponseEntity<UserDTO> User = restTemplate.getForEntity("http://UserMS/user/" + userID, UserDTO.class);
		if (User.getStatusCode() != HttpStatus.ACCEPTED) {
			throw new WeCareException(HttpStatus.BAD_REQUEST, "Invalid User ID");
		}
		ResponseEntity<CoachDTO> Coach = restTemplate.getForEntity("http://CoachMS/coach/" + coachID,
				CoachDTO.class);
		if (Coach.getStatusCode() != HttpStatus.ACCEPTED) {
			throw new WeCareException(HttpStatus.BAD_REQUEST, "Invalid Coach ID");
		}

		Booking booking = bookingRepo.save(getEntity(bookDTO));
		return getDTO(booking);
	}

	@Override
	public BookingDTO ResheduleAppointment(long bookingId, BookingDTO bookingDTO) {
		Booking booking = bookingRepo.findById(bookingId)
				.orElseThrow(() -> new ResourceNotFoundException("Booking", "Id", "" + bookingId));
		booking.setAppointmentDate(bookingDTO.getAppointmentDate());
		booking.setSlot(bookingDTO.getSlot());
		return getDTO(bookingRepo.save(booking));
	}

	@Override
	public void CancelAppointment(long bookingId) {
		bookingRepo.findById(bookingId).orElseThrow(()->new ResourceNotFoundException("Booking", "Id", ""+bookingId));
		bookingRepo.deleteById(bookingId);
	}
	
	@Override
	public List<BookingDTO> ShowAllAppointments(long userId) {
		
		ResponseEntity<UserDTO> User = restTemplate.getForEntity("http://localhost:9100/user/" + userId, UserDTO.class);
		if (User.getStatusCode() != HttpStatus.ACCEPTED) {
			throw new WeCareException(HttpStatus.BAD_REQUEST, "Invalid User ID");
		}
		
		List<Booking> bookings=bookingRepo.findAllByUserId(""+userId);
		List<BookingDTO> bookingdtos=new ArrayList<>();
		for(Booking temp:bookings) {
			bookingdtos.add(getDTO(temp));
		}
		return bookingdtos;
	}

	public Booking getEntity(BookingDTO bookDTO) {
		return mapper.map(bookDTO, Booking.class);
	}

	public BookingDTO getDTO(Booking book) {
		return mapper.map(book, BookingDTO.class);
	}

}
