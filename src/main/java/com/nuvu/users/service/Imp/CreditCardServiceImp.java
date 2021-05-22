package com.nuvu.users.service.Imp;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.nuvu.users.dto.ResponseDTO;
import com.nuvu.users.enums.ErrorEnum;
import com.nuvu.users.exceptions.CustomException;
import com.nuvu.users.model.CreditCard;
import com.nuvu.users.model.User;
import com.nuvu.users.repository.ICreditCardRepository;
import com.nuvu.users.repository.IUserRepository;
import com.nuvu.users.security.JWTValidator;
import com.nuvu.users.service.ICreditCardService;

@Service
public class CreditCardServiceImp implements ICreditCardService {
	
	@Autowired
	private ICreditCardRepository creditCardRepository;
	
	@Autowired
	private IUserRepository userRepository;

	@Override
	public CreditCard registerCreditCard(CreditCard creditCard, String token) {
		JWTValidator.validateRoleAdmin(token);
		
		// valida si el usuario existe
		Optional<User> user = userRepository.findById(creditCard.getUser().getId());
		if(!user.isPresent()) {
			throw new CustomException(HttpStatus.BAD_REQUEST, ErrorEnum.USER_NOT_REGISTERED);
		}
		creditCard.setRegistrationDate(new Date());
		return creditCardRepository.save(creditCard);
	}

	@Override
	public List<CreditCard> cardByIdUser(Integer idUser, String token) {
		JWTValidator.validateRoleAdmin(token);		
		return creditCardRepository.findByUserId(idUser);
	}

	@Override
	public ResponseDTO increaseQuota(String cardNumber, String token, CreditCard creditCard) {
		JWTValidator.validateRoleAdmin(token);		
		// busco la tarjeta
		Optional<CreditCard> card = creditCardRepository.findByCardNumber(cardNumber);
		if(!card.isPresent()) {
			throw new CustomException(HttpStatus.BAD_REQUEST, ErrorEnum.CARD_NOT_FOUND);
		}
		card.get().setMaximumQuota(creditCard.getMaximumQuota());
		creditCardRepository.save(card.get());
		return new ResponseDTO ("cupo incrementado exitosamente", true);
	}
	

}
