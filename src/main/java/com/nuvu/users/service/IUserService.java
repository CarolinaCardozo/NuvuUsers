package com.nuvu.users.service;

import java.util.List;
import java.util.Optional;

import com.nuvu.users.dto.ResponseDTO;
import com.nuvu.users.model.User;

public interface IUserService {

	User registerUser(User user);

	Optional<User> login(User data);

	List<User> listUsers(String token);

	User userById(Integer idUser, String token);
	
	ResponseDTO updateUser(Long identification, String token, User user);
}