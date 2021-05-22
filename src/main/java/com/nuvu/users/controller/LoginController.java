package com.nuvu.users.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nuvu.users.dto.UserDTO;
import com.nuvu.users.dto.UserLoginDTO;
import com.nuvu.users.model.User;
import com.nuvu.users.service.ITokenService;
import com.nuvu.users.service.IUserService;

@RestController
public class LoginController {

	@Autowired
	private IUserService userService;

	@Autowired
	private ModelMapper modelmapper;

	@Autowired
	ITokenService tokenService;

	@PostMapping("/registerUser")
	public ResponseEntity<User> registerUser(@Valid @RequestBody UserDTO userDTO) {
		return new ResponseEntity<>(userService.registerUser(modelmapper.map(userDTO, User.class)), HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody UserLoginDTO userLoginDTO) {
		List<GrantedAuthority> grantedAuthorities;
		Optional<User> user = userService.login(modelmapper.map(userLoginDTO, User.class));
		if(user.get().getRoleId() ==1) {
			grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("USER_ROLE");
		}else {
			grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ADMIN_ROLE");
		}
		
		return new ResponseEntity<>(tokenService.generateToken(user,grantedAuthorities), HttpStatus.OK);
	}
}
