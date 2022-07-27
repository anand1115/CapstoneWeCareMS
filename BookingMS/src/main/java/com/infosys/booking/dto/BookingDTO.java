package com.infosys.booking.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDTO {
	private long id;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate appointmentDate;
	@NotEmpty
	private String slot;
	@NotEmpty
	private String userId;
	@NotEmpty
	private String coachId;

}
