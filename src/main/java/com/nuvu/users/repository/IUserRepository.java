package com.nuvu.users.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nuvu.users.model.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {	
	
	Optional<User> findByIdentification(Long identification);
	Optional<User> findByIdentificationAndPassword(Long identification, String password);
}
