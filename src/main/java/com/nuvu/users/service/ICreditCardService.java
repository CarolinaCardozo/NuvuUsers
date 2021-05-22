package com.nuvu.users.service;

import java.util.List;

import com.nuvu.users.dto.ResponseDTO;
import com.nuvu.users.model.CreditCard;

public interface ICreditCardService {
	
	CreditCard registerCreditCard(CreditCard creditCard, String token); 
	List<CreditCard> cardByIdUser(Integer idUser, String token);
	ResponseDTO increaseQuota(String cardNumber, String token, CreditCard card);
}
