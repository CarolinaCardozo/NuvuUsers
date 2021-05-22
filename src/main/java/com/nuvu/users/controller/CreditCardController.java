package com.nuvu.users.controller;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nuvu.users.dto.CreditCardDTO;
import com.nuvu.users.dto.ResponseDTO;
import com.nuvu.users.model.CreditCard;
import com.nuvu.users.service.ICreditCardService;

@RestController
@RequestMapping("creditCard")
public class CreditCardController {

	@Autowired
	private ICreditCardService creditCardService;

	@Autowired
	private ModelMapper modelmapper;

	@PostMapping("/registerCard")
	public ResponseEntity<CreditCard> registerUser(@Valid @RequestBody CreditCardDTO creditCardDTO,
			@RequestHeader("Authorization") String token) {
		return new ResponseEntity<>(
				creditCardService.registerCreditCard(modelmapper.map(creditCardDTO, CreditCard.class), token),
				HttpStatus.CREATED);
	}

	@GetMapping(value = "/byIdUser/{idUser}")
	public ResponseEntity<List<CreditCard>> cardByIdUser(@PathVariable Integer idUser,
			@RequestHeader("Authorization") String token) {
		return new ResponseEntity<>(creditCardService.cardByIdUser(idUser, token), HttpStatus.OK);
	}

	@PatchMapping(value = "/increaseQuota/cardNumber/{cardNumber}")
	public ResponseEntity<ResponseDTO> increaseQuota(@PathVariable String cardNumber,
			@RequestHeader("Authorization") String token, @RequestBody CreditCard card) {
		return new ResponseEntity<>(creditCardService.increaseQuota(cardNumber, token, card), HttpStatus.OK);
	}

}
