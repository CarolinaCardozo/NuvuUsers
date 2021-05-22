package com.nuvu.users.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nuvu.users.dto.ResponseDTO;
import com.nuvu.users.model.User;
import com.nuvu.users.service.IUserService;

@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	private IUserService userService;

	@Autowired
	private ModelMapper modelmapper;
	
	@GetMapping("/getAllUsers")
	public ResponseEntity<List<User>> getAllUsers(@RequestHeader("Authorization") String token) {
		return new ResponseEntity<>(userService.listUsers(token),HttpStatus.OK);
	}
	
	@GetMapping(value = "/userById/{idUser}")
	public ResponseEntity<User> getUserById(@PathVariable Integer idUser, @RequestHeader("Authorization") String token)  {
		return new ResponseEntity<>(userService.userById(idUser, token ),HttpStatus.OK);
	}
	
	@PatchMapping(value = "/updateUser/identification/{identification}")
	public ResponseEntity<ResponseDTO> updateUser(@PathVariable Long identification,
			@RequestHeader("Authorization") String token, @RequestBody User user) {
		return new ResponseEntity<>(userService.updateUser(identification, token, user), HttpStatus.OK);
	}


}
