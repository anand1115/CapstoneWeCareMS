package com.infosys.user.dto;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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
public class UserDTO {
	private long id;
	@NotEmpty
	@Size(min=3,max=13,message="Please Provide Valid Name")
	private String name;
	private String gender;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate dateOfBirth;
	@NotEmpty
	@Size(min=5,max=10,message="Please Provide Valid Password")
	private String password;
	@NotNull
	private long mobileNumber;
	@NotEmpty
	@Email(message = "Please Provide Valid Email Id")
	private String email;
	@NotNull
	@Min(value = 100000)
	@Max(value = 999999)
	private int pincode;
	@NotEmpty
	@Size(min=3,max=20,message="Please Provide Valid city")
	private String city;
	@NotEmpty
	@Size(min=3,max=20,message="Please Provide Valid State")
	private String state;
	@NotEmpty
	@Size(min=3,max=20,message="Please Provide Valid Country")
	private String country;
}

