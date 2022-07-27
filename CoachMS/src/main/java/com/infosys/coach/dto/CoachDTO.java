package com.infosys.coach.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoachDTO {
	private long id;
	@NotEmpty
	@Size(min =3,max =50,message ="Please Provide Valid Name")
	private String name;
	private String gender;
	@JsonFormat(pattern ="dd-MM-yyyy")
	private LocalDate dateOfBirth;
	@NotEmpty
	@Size(min=5,max=20,message="Please Provide valid password")
	private String password;
	@NotNull
	private long mobileNumber;
	
	@NotEmpty
	@Size(min=3,max=50,message="Please Provide Valid Speciality")
	private String speciality;
}

