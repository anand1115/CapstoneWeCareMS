package com.infosys.booking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.infosys.booking.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long>{
	List<Booking> findAllByUserId(String id);
}

