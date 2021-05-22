package com.nuvu.users.dto;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

	@NotNull(message = "fullname no puede ser nulo")
	private String fullname;
	
	@NotNull(message = "identification no puede ser nulo")
	private Long identification;

	@Email
	private String email;

	private Date birthday;

	@NotNull(message = "phone no puede ser nulo")
	private String phone;
	
	@NotNull(message = "password no puede ser nulo")
	@NotEmpty(message = "password no puede ser vacío")
	private String password;
	
	@NotNull(message = "roleId no puede ser nulo")
	private Integer roleId;
}
