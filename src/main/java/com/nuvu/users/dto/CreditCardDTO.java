package com.nuvu.users.dto;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.nuvu.users.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditCardDTO {

	@NotNull(message = "cardNumber no puede ser nulo")
	@NotEmpty(message = "cardNumber no puede ser vacío")
	private String cardNumber;

	private Date expirationDate;

	@NotNull(message = "availableQuota no puede ser nulo")
	private Float availableQuota;

	@NotNull(message = "maximumQuota no puede ser nulo")
	private Float maximumQuota;
	
	@NotNull(message = "user no puede ser nulo")
	private User user;
}
