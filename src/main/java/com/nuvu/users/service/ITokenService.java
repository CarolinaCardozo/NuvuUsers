package com.nuvu.users.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;

import com.nuvu.users.model.User;

public interface ITokenService {
	
	/**
	 * Metodo con el cual se genera el token jwt para su posterior llamado a
	 * servicos
	 * 
	 * @param user
	 * @return
	 */
	String generateToken(Optional<User> user,  List<GrantedAuthority> grantedAuthorities);

}
