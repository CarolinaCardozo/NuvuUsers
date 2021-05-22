package com.nuvu.users.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nuvu.users.model.CreditCard;

@Repository
public interface ICreditCardRepository extends JpaRepository<CreditCard, Integer> {
	
	List<CreditCard> findByUserId(Integer idUser);
	Optional<CreditCard> findByCardNumber(String cardNumber);

}
