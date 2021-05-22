package com.nuvu.users.dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginDTO {
	
	@NotNull(message = "identification no puede ser nulo")
	private Long identification;
	
	@NotNull(message = "password no puede ser nulo")
	private String password;

}
